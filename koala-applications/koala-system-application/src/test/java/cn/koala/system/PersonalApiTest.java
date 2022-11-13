package cn.koala.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Houtaroy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class PersonalApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "test", authorities = {"user:write", "personal"})
  public void changePassword() throws Exception {
    UserEntity entity = UserEntity.builder()
      .id("999")
      .username("test")
      .nickname("test")
      .build();
    mockMvc.perform(post("/api/users")
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(entity))
    ).andExpect(status().isOk());
    ChangePasswordRequest request = ChangePasswordRequest.builder()
      .passwordOld("koala-projects")
      .passwordNew("123456")
      .build();
    mockMvc.perform(put("/api/personal/change-password")
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(request))
    ).andExpect(status().isOk());
    request.setPasswordOld("123456");
    request.setPasswordNew("koala-projects");
    mockMvc.perform(put("/api/personal/change-password")
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(request))
    ).andExpect(status().isOk());
  }
}
