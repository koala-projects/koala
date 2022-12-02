package cn.koala.system;

import cn.koala.builder.BuildRequest;
import cn.koala.builder.CodeTemplateGroupEntity;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.jdbc.JdbcTable;
import cn.koala.template.TemplateEntity;
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
public class CodeTemplateGroupApiTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/code-template-groups");
    CodeTemplateGroupEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of()).andExpect(status().isOk()).andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setName("测试代码模板-更新");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试代码模板-更新")))
      .andExpect(jsonPath("$.data.properties", hasSize(1)))
      .andExpect(jsonPath("$.data.properties[0].code", equalTo("package")))
      .andExpect(jsonPath("$.data.templates[0].name", equalTo("#(domain.name)")));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  @Test
  public void build() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/code-template-groups");
    CodeTemplateGroupEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.post("%s/build".formatted(entity.getId()), buildRequest()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data", hasSize(1)))
      .andExpect(jsonPath("$.data[0].name", equalTo("t_good")))
      .andExpect(jsonPath("$.data[0].content", equalTo("cn.koala")));
  }

  protected CodeTemplateGroupEntity entity() {
    return CodeTemplateGroupEntity.builder()
      .id("999")
      .code("test")
      .name("测试代码模板")
      .domainConverterId("enhanced-table")
      .properties(List.of(PropertyEntity.builder().code("package").name("包名").type("String").build()))
      .templates(List.of(TemplateEntity.builder().name("#(domain.name)").content("#(properties.package)").build()))
      .build();
  }

  protected BuildRequest buildRequest() {
    return BuildRequest.builder()
      .table(JdbcTable.builder().name("t_good").remarks("商品表").columns(List.of()).build())
      .properties(Map.of("package", "cn.koala", "name", "Good"))
      .build();
  }
}
