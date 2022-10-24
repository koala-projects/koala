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
public class DictionaryItemApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void add() throws Exception {
    DictionaryItemEntity dictionaryItem = DictionaryItemEntity.builder().id("1").name("测试字典项").content("test").dictionary(DictionaryEntity.builder().id("1").build()).build();
    mockMvc.perform(post("/api/dictionary-items").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dictionaryItem)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(2)
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void list() throws Exception {
    mockMvc.perform(get("/api/dictionary-items"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
  }

  @Test
  @Order(3)
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void update() throws Exception {
    DictionaryItemEntity dictionaryItem = DictionaryItemEntity.builder().id("1").name("测试字典项").content("test2").build();
    mockMvc.perform(put("/api/dictionary-items/1").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dictionaryItem)))
      .andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void load() throws Exception {
    mockMvc.perform(get("/api/dictionary-items/1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", equalTo("test2")));
  }

  @Test
  @Order(5)
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void delete() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/dictionary-items/1"))
      .andExpect(status().isOk());
  }
}