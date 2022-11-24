package cn.koala.system;

import cn.koala.datamodel.CreateDataRequest;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.test.MockMvcWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
public class DataApiTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper metadataWrapper = new MockMvcWrapper(mockMvc, "/api/metadata");
    MetadataEntity metadata = metadata();
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/data");
    Map<String, Object> data = data();
    metadataWrapper.add(metadata);
    wrapper.add(new CreateDataRequest(metadata, data)).andExpect(status().isOk());
    wrapper.list(Map.of("metadataId", metadata.getId())).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    data.put("name", "测试数据-更新");
    wrapper.update(data.get("id"), data).andExpect(status().isOk());
    wrapper.load(data.get("id")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试数据-更新")));
    wrapper.delete(data.get("id")).andExpect(status().isOk());
  }

  protected MetadataEntity metadata() {
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

  protected Map<String, Object> data() {
    Map<String, Object> result = new HashMap<>(2);
    result.put("id", "999");
    result.put("name", "测试数据");
    result.put("age", 1);
    return result;
  }
}
