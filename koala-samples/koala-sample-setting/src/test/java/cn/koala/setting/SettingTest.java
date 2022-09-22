package cn.koala.setting;

import cn.koala.datamodel.DataService;
import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.datamodel.PersistentProperty;
import cn.koala.datamodel.PropertyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SettingTest {
  @Autowired
  private SettingRegistry settingRegistry;
  @Autowired
  private SettingService settingService;
  @Autowired
  private DataService dataService;
  @Autowired
  private MetadataService metadataService;

  @Test
  @Order(1)
  public void register() {
    PersistentMetadata metadata = PersistentMetadata.builder().id("999").name("系统设置").build();
    metadata.setProperties(List.of(
      PersistentProperty.builder().id("999-1").code("default-password").name("默认密码").type(PropertyType.STRING).build()
    ));
    settingRegistry.registerSetting(metadata, Map.of("default-password", "koala"));
  }

  @Test
  @Order(2)
  public void update() {
    Assertions.assertDoesNotThrow(
      () -> settingService.update("999", Map.of("default-password", "koala-update"))
    );
  }

  @Test
  @Order(3)
  public void load() {
    Optional<Map<String, Object>> setting = settingService.load("999");
    Assertions.assertTrue(setting.isPresent());
    Assertions.assertEquals(setting.get().get("default-password"), "koala-update");
  }

  @Test
  @Order(4)
  public void loadByKey() {
    Optional<Object> setting = settingService.loadByKey("system.default-password");
    Assertions.assertTrue(setting.isPresent());
    Assertions.assertEquals(setting.get(), "koala-update");
    clean();
  }

  protected void clean() {
    dataService.list(new HashMap<>()).forEach(data -> dataService.delete(data.getId()));
    metadataService.delete(PersistentMetadata.builder().id("999").build());
  }
}
