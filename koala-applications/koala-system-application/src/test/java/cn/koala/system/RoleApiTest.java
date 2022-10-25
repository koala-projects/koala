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
public class RoleApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void add() throws Exception {
    RoleEntity entity = RoleEntity.builder().id("999").code("test").name("测试角色").build();
    mockMvc.perform(post("/api/roles").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void list() throws Exception {
    mockMvc.perform(get("/api/roles"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
  }

  @Test
  @Order(3)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void update() throws Exception {
    RoleEntity entity = RoleEntity.builder().id("999").code("test2").name("测试角色").build();
    mockMvc.perform(put("/api/roles/999").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void authorize() throws Exception {
    mockMvc.perform(put("/api/roles/999/authorize").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(List.of("role:write"))))
      .andExpect(status().isOk());
  }

  @Test
  @Order(5)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void permissionIds() throws Exception {
    mockMvc.perform(get("/api/roles/999/permission-ids"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data", hasSize(1)));
  }

  @Test
  @Order(6)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void load() throws Exception {
    mockMvc.perform(get("/api/roles/999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.code", equalTo("test2")));
  }

  @Test
  @Order(7)
  @WithMockUser(username = "admin", authorities = {"role:read", "role:write"})
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/roles/999"))
      .andExpect(status().isOk());
  }
}
