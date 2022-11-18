package cn.koala.system;

import cn.koala.template.TemplateEntity;
import cn.koala.template.TemplateGroupEntity;
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
public class TemplateGroupApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/template-groups");
    TemplateGroupEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of("name", "测试")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setName("测试模板组-更新");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试模板组-更新")))
      .andExpect(jsonPath("$.data.templates", hasSize(1)));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  protected TemplateGroupEntity entity() {
    return TemplateGroupEntity.builder()
      .id("999")
      .name("测试模板组")
      .templates(List.of(TemplateEntity.builder().name("测试模板").content("#(name)").build()))
      .build();
  }
}
