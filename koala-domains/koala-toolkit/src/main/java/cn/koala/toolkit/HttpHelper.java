package cn.koala.toolkit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * HTTP帮助类
 *
 * @author Houtaroy
 */
public abstract class HttpHelper {
  public static final String[] IP_HEADERS = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
  public static final String UNKNOWN = "unknown";
  public static final String IP_SEPARATOR = ",";

  public static String getRequestIp(@NonNull HttpServletRequest request) {
    return Arrays.stream(IP_HEADERS)
      .map(request::getHeader)
      .filter(header -> StringUtils.isNotBlank(header) && !UNKNOWN.equals(header))
      .findFirst()
      .map(HttpHelper::getFirstNonUnknownIp)
      .orElse(getFirstNonUnknownIp(request.getRemoteAddr()));
  }

  public static String getFirstNonUnknownIp(@NonNull String ips) {
    if (ips.contains(IP_SEPARATOR)) {
      return Arrays.stream(ips.split(IP_SEPARATOR)).filter(ip -> !UNKNOWN.equals(ip)).findFirst().orElse(ips);
    }
    return ips;
  }
}
