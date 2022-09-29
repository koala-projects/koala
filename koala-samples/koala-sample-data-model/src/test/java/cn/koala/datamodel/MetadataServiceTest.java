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
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MetadataServiceTest {
  @Autowired
  private MetadataService metadataService;
  private static MetadataEntity TEST_DATA;

  @Test
  @Order(1)
  public void add() {
    TEST_DATA = MetadataEntity.builder().id("999").code("test").name("新增").description("新增").build();
    TEST_DATA.setProperties(List.of(PropertyEntity.builder().code("add").name("新增").description("新增").type(PropertyType.STRING).build()));
    Assertions.assertDoesNotThrow(() -> metadataService.add(TEST_DATA));
  }

  @Test
  @Order(2)
  public void update() {
    TEST_DATA.setName("更新");
    TEST_DATA.setProperties(List.of(PropertyEntity.builder().code("update").name("更新").description("更新").type(PropertyType.STRING).build()));
    Assertions.assertDoesNotThrow(() -> metadataService.update(TEST_DATA));
  }

  @Test
  @Order(3)
  public void list() {
    Assertions.assertEquals(metadataService.list(new HashMap<>(), PageRequest.of(0, 10)).getTotalElements(), 1);
  }

  @Test
  @Order(4)
  public void load() {
    Optional<Metadata> persistence = metadataService.load("999");
    Assertions.assertTrue(persistence.isPresent());
    Assertions.assertEquals(persistence.get().getName(), "更新");
  }

  @Test
  @Order(5)
  public void delete() {
    Assertions.assertDoesNotThrow(() -> metadataService.delete(TEST_DATA));
  }
}
