package cn.koala.system;

import cn.koala.data.DataSourceEntity;
import cn.koala.test.MockMvcWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Houtaroy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class DataSourceApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void crud() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/data-sources");
    DataSourceEntity entity = entity();
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.list(Map.of("code", "test")).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    entity.setName("测试数据源-更新");
    wrapper.update(entity.getId(), entity).andExpect(status().isOk());
    wrapper.load(entity.getId()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data.name", equalTo("测试数据源-更新")));
    wrapper.delete(entity.getId()).andExpect(status().isOk());
  }

  @Test
  public void data() throws Exception {
    MockMvcWrapper wrapper = new MockMvcWrapper(mockMvc, "/api/data-sources");
    DataSourceEntity entity = entity();
    wrapper.post("is-connectable", entity).andExpect(status().isOk())
      .andExpect(jsonPath("$.data", equalTo(true)));
    wrapper.add(entity).andExpect(status().isOk());
    wrapper.get("%s/catalog-names".formatted(entity.getId()), Map.of()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data", notNullValue()))
      .andExpect(jsonPath("$.data", hasItem("koala")));
    wrapper.get("%s/catalogs/koala/tables".formatted(entity.getId()), Map.of()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data", notNullValue()));
    wrapper.get("%s/catalogs/koala/tables/t_user/columns".formatted(entity.getId()), Map.of()).andExpect(status().isOk())
      .andExpect(jsonPath("$.data", notNullValue()));
  }

  protected DataSourceEntity entity() {
    return DataSourceEntity.builder()
      .id("999")
      .name("测试数据源")
      .url("jdbc:mysql://localhost:3306")
      .username("root")
      .password("123456")
      .build();
  }
}
