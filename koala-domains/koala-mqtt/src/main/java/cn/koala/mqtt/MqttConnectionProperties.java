package cn.koala.mqtt;

import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class MqttConnectionProperties {
  private String username;
  private String password;
  private String[] uris;
}
