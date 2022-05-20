package cn.houtaroy.koala.sample.mqtt;

import cn.koala.mqtt.MqttTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Houtaroy
 */
@RequestMapping("/api/v1/send")
@RequiredArgsConstructor
@RestController
public class SendController {

  public static final int QOS_2 = 2;
  private final MqttTemplate mqttTemplate;

  /**
   * 发送消息接口
   *
   * @return 发送结果
   */
  @GetMapping
  public String send() {
    mqttTemplate.send("默认主题");
    mqttTemplate.send("topic-1", "指定主题, 默认qos: 1");
    mqttTemplate.send("topic-2", QOS_2, "指定主题, qos: 2");
    return "OK";
  }

  /**
   * 动态添加订阅
   *
   * @param topic 主题
   * @return 添加结果
   */
  @PostMapping("{topic}")
  public String add(@PathVariable("topic") String topic) {
    mqttTemplate.addTopic(topic, new MqttMessageListener());
    return "OK";
  }

  /**
   * 动态移除监听
   *
   * @param topic 主题
   * @return 移除结果
   */
  @DeleteMapping("{topic}")
  public String remove(@PathVariable("topic") String topic) {
    mqttTemplate.removeTopic(topic);
    return "OK";
  }
}
