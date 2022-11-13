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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Houtaroy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class DictionaryItemApiTest {
  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "admin", authorities = {"dictionary:read", "dictionary:write"})
  public void crud() throws Exception {
    String url = "/api/dictionary-items";
    DictionaryItemEntity entity = DictionaryItemEntity.builder()
      .id("1")
      .name("测试字典项")
      .content("test")
      .dictionary(DictionaryEntity.builder().id("1").build())
      .build();
    mockMvc.perform(post(url)
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(entity))
    ).andExpect(status().isOk());
    mockMvc.perform(get(url))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setContent("test2");
    mockMvc.perform(put("%s/%s".formatted(url, entity.getId()))
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(entity))
    ).andExpect(status().isOk());
    mockMvc.perform(get("%s/%s".formatted(url, entity.getId())))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", equalTo("test2")));
    mockMvc.perform(delete("%s/%s".formatted(url, entity.getId())))
      .andExpect(status().isOk());
  }
}
