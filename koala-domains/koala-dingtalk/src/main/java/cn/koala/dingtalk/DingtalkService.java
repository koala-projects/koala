package cn.koala.dingtalk;

import com.aliyun.teaopenapi.Client;
import com.aliyun.teaopenapi.models.Config;
import com.dingtalk.api.DefaultDingTalkClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;

import java.util.Optional;

/**
 * @author Houtaroy
 */
public interface DingtalkService {
  /**
   * 生成请求客户端
   *
   * @param uri 请求地址
   * @return 客户端
   */
  DefaultDingTalkClient client(String uri);

  /**
   * 生成请求客户端
   *
   * @param clientClass 客户端类
   * @param <T>         客户端类型
   * @return 客户端
   */
  <T extends Client> Optional<T> client(Class<T> clientClass);

  /**
   * 生成请求客户端
   *
   * @param clientClass 客户端类
   * @param config      客户端配置
   * @param <T>         客户端类型
   * @return 客户端
   */
  <T extends Client> Optional<T> client(Class<T> clientClass, Config config);

  /**
   * 执行请求
   *
   * @param uri     请求地址
   * @param request 请求
   * @param <T>     响应类型
   * @return 响应
   * @throws Exception 异常
   */
  <T extends TaobaoResponse> T execute(String uri, TaobaoRequest<T> request) throws Exception;
}
