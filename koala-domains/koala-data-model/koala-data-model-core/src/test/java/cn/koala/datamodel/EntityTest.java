package cn.koala.datamodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class EntityTest {
  @Test
  public void create() {
    List<PropertyEntity> properties = List.of(
      PropertyEntity.builder().code("name").name("名称").description("名称").type(PropertyType.STRING).build(),
      PropertyEntity.builder().code("age").name("年龄").description("年龄").type(PropertyType.INTEGER).build()
    );
    MetadataEntity metadata = MetadataEntity.builder().id("999").code("test").name("测试").description("测试").build();
    metadata.setProperties(properties);
    Map<String, Object> contents = Map.of("name", "测试数据", "age", 18);
    DataEntity data = DataEntity.fromMetaDataAndContents(metadata, contents);
    Assertions.assertNotNull(data.getId());
    Assertions.assertEquals(data.getElements().get(0).getProperty(), properties.get(0));
    Assertions.assertEquals(data.getElements().get(1).getContent(), "18");
  }
}