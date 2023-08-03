package cn.koala.resource.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源服务配置属性
 *
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "koala.security.resource-server")
@Data
public class ResourceServerProperties {

  private List<String> permitAllPatterns = new ArrayList<>();
}
