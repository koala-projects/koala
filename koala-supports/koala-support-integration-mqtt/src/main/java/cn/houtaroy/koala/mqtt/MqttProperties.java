package cn.houtaroy.koala.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Houtaroy
 */
@ConfigurationProperties(prefix = "spring.mqtt")
@Data
public class MqttProperties {

  private String clientId = "mqtt";
  @NestedConfigurationProperty
  MqttConnectionProperties connection;
  private String[] topics;
  private int[] qos;
  private int defaultQos = 1;
  private String defaultTopic = "mqtt-topic";
  private boolean async = false;
  private boolean asyncEvents = false;
}
