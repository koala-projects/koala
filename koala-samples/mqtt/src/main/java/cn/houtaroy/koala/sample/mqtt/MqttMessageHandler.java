package cn.houtaroy.koala.sample.mqtt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * @author Houtaroy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MqttMessageHandler implements MessageHandler {

  @Override
  @ServiceActivator(inputChannel = "mqttInboundChannel")
  public void handleMessage(Message<?> message) throws MessagingException {
    LOGGER.info(message.getPayload().toString());
  }
}
