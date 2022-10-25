package cn.koala.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void add() throws Exception {
    UserEntity entity = UserEntity.builder().id("999").username("test").password("12345").nickname("测试用户").build();
    mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void list() throws Exception {
    mockMvc.perform(get("/api/users"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
  }

  @Test
  @Order(3)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void update() throws Exception {
    UserEntity entity = UserEntity.builder().id("999").username("test2").password("12345").nickname("测试用户").build();
    mockMvc.perform(put("/api/users/999").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void authorize() throws Exception {
    mockMvc.perform(put("/api/users/999/role-ids").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(List.of("999"))))
      .andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void permissionIds() throws Exception {
    mockMvc.perform(get("/api/users/999/role-ids"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data", hasSize(1)));
  }

  @Test
  @Order(6)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void load() throws Exception {
    mockMvc.perform(get("/api/users/999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.username", equalTo("test2")));
  }

  @Test
  @Order(7)
  @WithMockUser(username = "admin", authorities = {"user:read", "user:write"})
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/999"))
      .andExpect(status().isOk());
  }
}
