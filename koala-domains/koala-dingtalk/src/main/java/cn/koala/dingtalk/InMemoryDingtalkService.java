package cn.koala.dingtalk;

import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.teaopenapi.Client;
import com.aliyun.teaopenapi.models.Config;
import com.dingtalk.api.DefaultDingTalkClient;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@Slf4j
@SuppressWarnings("PMD.ServiceOrDaoClassShouldEndWithImplRule")
public class InMemoryDingtalkService implements DingtalkService {
  public static final Config NEW_CLIENT_DEFAULT_CONFIG = new Config();
  private final DingtalkProperties properties;
  private final Map<String, DefaultDingTalkClient> oldClients = Maps.newConcurrentMap();
  private final Map<Class<? extends Client>, Client> newClients = Maps.newConcurrentMap();

  private DingtalkAccessToken token = new DingtalkAccessToken();

  static {
    NEW_CLIENT_DEFAULT_CONFIG.protocol = "https";
    NEW_CLIENT_DEFAULT_CONFIG.regionId = "central";
  }

  /**
   * 构造函数
   *
   * @param properties 钉钉配置
   */
  public InMemoryDingtalkService(DingtalkProperties properties) {
    this.properties = properties;
  }

  @Override
  public DefaultDingTalkClient client(String uri) {
    return oldClients.computeIfAbsent(uri, key -> new DefaultDingTalkClient(uri));
  }

  @Override
  public <T extends Client> Optional<T> client(Class<T> clientClass) {
    return client(clientClass, NEW_CLIENT_DEFAULT_CONFIG);
  }

  @Override
  public <T extends Client> Optional<T> client(Class<T> clientClass, Config config) {
    Client result = newClients.get(clientClass);
    if (result == null) {
      try {
        result = clientClass.getConstructor(Config.class).newInstance(config);
        newClients.put(clientClass, result);
      } catch (Exception e) {
        LOGGER.error("找不到指定的客户端类型: {}", clientClass.getName(), e);
      }
    }
    return Optional.ofNullable(result).map(clientClass::cast);
  }

  @Override
  public <T extends TaobaoResponse> T execute(String uri, TaobaoRequest<T> request) throws Exception {
    return execute(uri, request, token());
  }

  /**
   * 执行请求
   *
   * @param uri     请求地址
   * @param request 请求
   * @param token   请求token
   * @param <T>     请求结果类型
   * @return 请求结果
   * @throws ApiException 请求异常
   */
  public <T extends TaobaoResponse> T execute(String uri, TaobaoRequest<T> request, String token) throws ApiException {
    return client(uri).execute(request, token);
  }

  /**
   * 获取token
   *
   * @return token
   * @throws Exception 异常
   */
  public String token() throws Exception {
    return token(properties.getAppKey(), properties.getAppSecret());
  }

  /**
   * 获取token
   *
   * @param appKey    appKey
   * @param appSecret appSecret
   * @return token
   * @throws Exception 异常
   */
  public String token(String appKey, String appSecret) throws Exception {
    if (token.isExpired()) {
      Optional<com.aliyun.dingtalkoauth2_1_0.Client> client = client(com.aliyun.dingtalkoauth2_1_0.Client.class);
      if (client.isPresent()) {
        GetAccessTokenRequest request = new GetAccessTokenRequest();
        request.setAppKey(appKey).setAppSecret(appSecret);
        GetAccessTokenResponse response = client.get().getAccessToken(request);
        token = new DingtalkAccessToken(response.getBody());
      }
    }
    return token.getValue();
  }
}
