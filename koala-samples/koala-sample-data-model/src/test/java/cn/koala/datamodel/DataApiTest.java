package cn.koala.datamodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataApiTest {
  @Autowired
  private MetadataService metadataService;
  @Autowired
  private DataService dataService;

  @Test
  @Order(1)
  public void add() {
    PersistentMetadata metadata = PersistentMetadata.builder().id("999").code("metadata").name("元数据").build();
    metadata.setProperties(List.of(
      PersistentProperty.builder().id("999-1").code("name").name("姓名").type(PropertyType.STRING).build(),
      PersistentProperty.builder().id("999-2").code("age").name("年龄").type(PropertyType.INTEGER).build()
    ));
    metadataService.add(metadata);
    Assertions.assertDoesNotThrow(() -> dataService.add(metadata, Map.of("_koala_data_id", "999", "name", "考拉", "age", 10)));
  }

  @Test
  @Order(2)
  public void update() {
    Assertions.assertDoesNotThrow(() -> dataService.update("999", Map.of("_koala_data_id", "999", "name", "考拉", "age", 12)));
  }

  @Test
  @Order(3)
  public void list() {
    Assertions.assertEquals(dataService.list(new HashMap<>(), PageRequest.of(0, 10)).getTotalElements(), 1);
  }

  @Test
  @Order(4)
  public void load() {
    Optional<Data> persistence = dataService.load("999");
    Assertions.assertTrue(persistence.isPresent());
    Map<String, Object> map = persistence.get().toMap();
    Assertions.assertEquals(map.get("name"), "考拉");
    Assertions.assertEquals(map.get("age"), 12);
  }

  @Test
  @Order(5)
  public void delete() {
    metadataService.delete(PersistentMetadata.builder().id("999").build());
    Assertions.assertDoesNotThrow(() -> dataService.delete("999"));
  }
}
