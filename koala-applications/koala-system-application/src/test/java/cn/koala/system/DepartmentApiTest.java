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
public class DepartmentApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void add() throws Exception {
    DepartmentEntity entity = DepartmentEntity.builder().id("999").code("test").name("测试部门").build();
    mockMvc.perform(post("/api/departments").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void list() throws Exception {
    mockMvc.perform(get("/api/departments"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
  }

  @Test
  @Order(3)
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void update() throws Exception {
    DepartmentEntity entity = DepartmentEntity.builder().id("999").code("test2").name("测试权限").build();
    mockMvc.perform(put("/api/departments/999").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(entity)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void load() throws Exception {
    mockMvc.perform(get("/api/departments/999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.code", equalTo("test2")));
  }

  @Test
  @Order(5)
  @WithMockUser(username = "admin", authorities = {"department:read", "department:write"})
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/departments/999"))
      .andExpect(status().isOk());
  }
}
