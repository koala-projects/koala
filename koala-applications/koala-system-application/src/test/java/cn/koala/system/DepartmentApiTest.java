package cn.koala.system;

import cn.koala.test.MockMvcWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
public class DepartmentApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/departments");
    DepartmentEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of("code", "test")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setCode("test2");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.code", equalTo("test2")));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  protected DepartmentEntity entity() {
    return DepartmentEntity.builder()
      .id("999")
      .code("test")
      .name("测试部门")
      .build();
  }
}
