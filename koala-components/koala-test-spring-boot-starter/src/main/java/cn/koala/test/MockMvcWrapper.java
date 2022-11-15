package cn.koala.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class MockMvcWrapper {
  private final MockMvc mockMvc;
  private final String url;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * 根据条件分页查询接口测试请求
   *
   * @param parameters 查询条件
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public ResultActions list(Map<String, Object> parameters) throws Exception {
    MockHttpServletRequestBuilder builder = get(url);
    parameters.forEach((key, value) -> builder.queryParam(key, value.toString()));
    return mockMvc.perform(builder);
  }

  /**
   * 根据id查询数据测试请求
   *
   * @param id  id
   * @param <K> id类型
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public <K> ResultActions load(K id) throws Exception {
    return mockMvc.perform(get("%s/%s".formatted(url, id)));
  }

  /**
   * 新增测试请求
   *
   * @param entity 新增数据实体
   * @param <E>    数据实体类型
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public <E> ResultActions add(E entity) throws Exception {
    return mockMvc.perform(post(url)
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(entity))
    );
  }

  /**
   * 更新测试请求
   *
   * @param id     id
   * @param entity 数据实体
   * @param <K>    id类型
   * @param <E>    数据实体类型
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public <K, E> ResultActions update(K id, E entity) throws Exception {
    return mockMvc.perform(put("%s/%s".formatted(url, id))
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(entity))
    );
  }

  /**
   * 删除测试请求
   *
   * @param id  id
   * @param <K> id类型
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public <K> ResultActions delete(K id) throws Exception {
    return mockMvc.perform(MockMvcRequestBuilders.delete("%s/%s".formatted(url, id)));
  }
}
