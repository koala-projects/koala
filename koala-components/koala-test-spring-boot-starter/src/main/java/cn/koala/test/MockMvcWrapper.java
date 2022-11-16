package cn.koala.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class MockMvcWrapper {
  private final MockMvc mockMvc;
  private final String domain;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * 根据条件分页查询接口测试请求
   *
   * @param parameters 查询条件
   * @return 测试请求
   * @throws Exception 请求异常
   */
  public ResultActions list(Map<String, Object> parameters) throws Exception {
    return get("", parameters);
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
    return get(id.toString(), Map.of());
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
    return post("", entity);
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
    return mockMvc.perform(put("%s/%s".formatted(domain, id))
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
    return mockMvc.perform(MockMvcRequestBuilders.delete("%s/%s".formatted(domain, id)));
  }

  /**
   * 通用GET请求
   *
   * @param url        请求路径
   * @param parameters 请求参数
   * @return 请求
   * @throws Exception 异常
   */
  public ResultActions get(String url, Map<String, Object> parameters) throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(getFullUrl(url));
    parameters.forEach((key, value) -> builder.queryParam(key, value.toString()));
    return mockMvc.perform(builder);
  }

  /**
   * 通用POST请求
   *
   * @param url     请求路径
   * @param content 请求内容
   * @return 请求
   * @throws Exception 异常
   */
  public ResultActions post(String url, Object content) throws Exception {
    return mockMvc.perform(MockMvcRequestBuilders.post(getFullUrl(url))
      .contentType(MediaType.APPLICATION_JSON)
      .content(mapper.writeValueAsString(content))
    );
  }

  /**
   * 获取请求全路径
   *
   * @param url 请求路径
   * @return 请求全路径
   */
  protected String getFullUrl(String url) {
    return StringUtils.hasLength(url) ? "%s/%s".formatted(domain, url) : domain;
  }
}
