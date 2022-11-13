package cn.koala.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Houtaroy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class UserinfoApiTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", authorities = {"personal"})
  public void load() throws Exception {
    mockMvc.perform(get("/api/userinfo"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.username", equalTo("admin")))
      .andExpect(jsonPath("$.data.authorities", hasSize(11)))
      .andExpect(jsonPath("$.data.authorities[10].authority", equalTo("personal")));
  }
}
