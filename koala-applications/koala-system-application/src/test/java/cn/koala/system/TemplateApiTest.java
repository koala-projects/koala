package cn.koala.system;

import cn.koala.template.TemplateEntity;
import cn.koala.test.MockMvcWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

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
public class TemplateApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/templates");
    TemplateEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of("name", "测试")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setName("测试模板-更新");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试模板-更新")));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  @Test
  public void render() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/templates");
    TemplateEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.post("%s/render".formatted(entity.getId()), Map.of("name", "名称")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data['测试模板']", equalTo("名称")));
  }

  protected TemplateEntity entity() {
    return TemplateEntity.builder()
      .id("999")
      .name("测试模板")
      .content("#(name)")
      .build();
  }
}
