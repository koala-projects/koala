package cn.houtaroy.koala.sample.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author Houtaroy
 */
@Slf4j
public class MqttMessageListener implements IMqttMessageListener {

  @Override
  public void messageArrived(String topic, MqttMessage message) {
    LOGGER.info("主题[{}]: {}", topic, message);
  }
}
