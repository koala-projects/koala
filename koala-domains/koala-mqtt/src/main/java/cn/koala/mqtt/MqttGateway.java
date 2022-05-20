package cn.koala.mqtt;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author Houtaroy
 */
public interface MqttGateway {
  /**
   * 发送消息
   *
   * @param payload 消息
   */
  void send(String payload);

  /**
   * 向指定主题发送消息
   *
   * @param topic   主题
   * @param payload 消息
   */
  void send(@Header(MqttHeaders.TOPIC) String topic, String payload);

  /**
   * 向指定主题发送指定QOS的消息
   *
   * @param topic   主题
   * @param qos     qos
   * @param payload 消息
   */
  void send(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
