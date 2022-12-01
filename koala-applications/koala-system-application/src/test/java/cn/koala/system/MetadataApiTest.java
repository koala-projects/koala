package cn.koala.system;

import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.test.MockMvcWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Houtaroy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class MetadataApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/metadata");
    MetadataEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of("code", "test")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setName("测试元数据-更新");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试元数据-更新")))
      .andExpect(jsonPath("$.data.properties", hasSize(2)))
      .andExpect(jsonPath("$.data.properties[0].code", equalTo("name")));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  protected MetadataEntity entity() {
    return MetadataEntity.builder()
      .id("999")
      .code("test")
      .name("测试元数据")
      .properties(List.of(
        PropertyEntity.builder().id("99901").code("name").name("名称").type("String").build(),
        PropertyEntity.builder().id("99902").code("age").name("年龄").type("Integer").build()
      ))
      .build();
  }
}
