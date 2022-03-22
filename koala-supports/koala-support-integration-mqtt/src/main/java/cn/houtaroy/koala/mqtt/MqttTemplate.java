package cn.houtaroy.koala.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Houtaroy
 */
public class MqttTemplate {

  private final MqttAdapter adapter;
  private final MqttGateway gateway;

  /**
   * 构造方法
   *
   * @param adapter Mqtt入站适配器
   * @param gateway Mqtt消息网关
   */
  public MqttTemplate(MqttAdapter adapter, MqttGateway gateway) {
    this.adapter = adapter;
    this.gateway = gateway;
  }

  /**
   * 增加监听主题
   *
   * @param topic 监听主题
   */
  public void addTopic(String topic) {
    addTopic(topic, 1);
  }

  /**
   * 增加监听主题, 并指定qos
   *
   * @param topic 监听主题
   * @param qos   qos
   */
  public void addTopic(String topic, int qos) {
    Assert.isTrue(StringUtils.hasLength(topic), "主题不能为空");
    adapter.addTopic(topic, qos);
  }

  /**
   * 增加监听主题, 并指定监听器
   *
   * @param topic    监听主题
   * @param listener 监听器
   */
  public void addTopic(String topic, IMqttMessageListener listener) {
    addTopic(topic, 1, listener);
  }

  /**
   * 增加监听主题, 并指定qos和监听器
   *
   * @param topic    监听主题
   * @param qos      qos
   * @param listener 监听器
   */
  public void addTopic(String topic, int qos, IMqttMessageListener listener) {
    Assert.isTrue(StringUtils.hasLength(topic), "主题不能为空");
    adapter.addTopic(topic, qos, listener);
  }

  /**
   * 批量增加监听主题
   *
   * @param topics 监听主题数组
   */
  public void addTopics(String... topics) {
    adapter.addTopic(topics);
  }

  /**
   * 批量增加监听主题, 并指定qos
   *
   * @param topics 监听主题数组
   * @param qos    qos数组
   */
  public void addTopics(String[] topics, int[] qos) {
    adapter.addTopics(topics, qos);
  }

  /**
   * 移除监听主题
   *
   * @param topic 监听主题
   */
  public void removeTopic(String topic) {
    adapter.removeTopic(topic);
  }

  /**
   * 批量移除监听主题
   *
   * @param topics 监听主题数组
   */
  public void removeTopics(String... topics) {
    adapter.removeTopic(topics);
  }

  /**
   * 发送消息
   *
   * @param message 消息
   */
  public void send(String message) {
    gateway.send(message);
  }

  /**
   * 向指定主题发送消息
   *
   * @param topic   主题
   * @param message 消息
   */
  public void send(String topic, String message) {
    gateway.send(topic, message);
  }

  /**
   * 向指定主题发送指定QOS的消息
   *
   * @param topic   主题
   * @param qos     qos
   * @param message 消息
   */
  public void send(String topic, int qos, String message) {
    gateway.send(topic, qos, message);
  }
}
