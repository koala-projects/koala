package cn.koala.sample.system;

import cn.koala.system.mybatis.RoleEntity;
import cn.koala.system.mybatis.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", authorities = {"users:read", "users:write"})
  public void add() throws Exception {
    UserEntity user = UserEntity.builder().id("2").username("test").name("测试用户")
      .roles(List.of(RoleEntity.builder().id("1").build())).build();
    mockMvc.perform(
      post("/api/users").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
    ).andExpect(status().isOk());
    mockMvc.perform(get("/api/users/2")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.username", equalTo("test")))
      .andExpect(jsonPath("$.data.roles", hasSize(1)))
      .andExpect(jsonPath("$.data.createTime", notNullValue()));
  }

  @Test
  @WithMockUser(username = "admin", authorities = {"users:read", "users:write"})
  public void update() throws Exception {
    UserEntity user = UserEntity.builder().username("admin2").build();
    mockMvc.perform(
      put("/api/users/1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
    ).andExpect(status().isOk());
    mockMvc.perform(get("/api/users/1")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.username", equalTo("admin2")))
      .andExpect(jsonPath("$.data.lastModifyTime", notNullValue()));
  }

  @Test
  @WithMockUser(username = "admin", authorities = {"users:read", "users:write"})
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")).andExpect(status().isOk());
    mockMvc.perform(get("/api/users")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.totalElements", equalTo(0)));
  }
}
