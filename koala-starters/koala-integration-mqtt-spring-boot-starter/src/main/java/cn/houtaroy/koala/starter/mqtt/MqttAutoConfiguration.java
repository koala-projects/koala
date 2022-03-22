package cn.houtaroy.koala.starter.mqtt;

import cn.houtaroy.koala.mqtt.MqttAdapter;
import cn.houtaroy.koala.mqtt.MqttGateway;
import cn.houtaroy.koala.mqtt.MqttProperties;
import cn.houtaroy.koala.mqtt.MqttTemplate;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.gateway.GatewayProxyFactoryBean;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
@RequiredArgsConstructor
public class MqttAutoConfiguration {

  private final MqttProperties properties;

  /**
   * mqtt客户端工厂的bean
   *
   * @return mqtt客户端工厂
   */
  @Bean
  @ConditionalOnMissingBean
  public MqttPahoClientFactory mqttPahoClientFactory() {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(properties.getConnection().getUris());
    options.setUserName(properties.getConnection().getUsername());
    options.setPassword(Optional.ofNullable(properties.getConnection().getPassword()).orElse("").toCharArray());
    factory.setConnectionOptions(options);
    return factory;
  }

  /**
   * 入站通道的bean
   *
   * @return 入站通道
   */
  @Bean
  public MessageChannel mqttInboundChannel() {
    return new DirectChannel();
  }

  /**
   * mqtt入站适配器的bean
   *
   * @param factory mqtt客户端工厂
   * @return mqtt入站适配器
   */
  @Bean
  @ConditionalOnMissingBean
  public MqttAdapter mqttAdapter(MqttPahoClientFactory factory) {
    MqttAdapter adapter = new MqttAdapter(
      String.format("%s-adapter-%s", properties.getClientId(), UUID.randomUUID()),
      factory, properties.getTopics()
    );
    adapter.setConverter(new DefaultPahoMessageConverter());
    adapter.setQos(properties.getQos());
    adapter.setOutputChannel(mqttInboundChannel());
    return adapter;
  }

  /**
   * 出站通道的bean
   *
   * @return 出站通道
   */
  @Bean
  public MessageChannel mqttOutboundChannel() {
    return new DirectChannel();
  }

  /**
   * mqtt出站拦截器的bean
   *
   * @param factory mqtt客户端工厂
   * @return mqtt出站拦截器
   */
  @Bean
  @ConditionalOnMissingBean
  @ServiceActivator(inputChannel = "mqttOutboundChannel")
  public MqttPahoMessageHandler mqttHandler(MqttPahoClientFactory factory) {
    MqttPahoMessageHandler handler = new MqttPahoMessageHandler(
      String.format("%s-handler-%s", properties.getClientId(), UUID.randomUUID()),
      factory
    );
    handler.setDefaultTopic(properties.getDefaultTopic());
    handler.setDefaultQos(properties.getDefaultQos());
    handler.setAsync(properties.isAsync());
    handler.setAsyncEvents(properties.isAsyncEvents());
    return handler;
  }

  /**
   * mqtt消息网关的bean
   *
   * @return mqtt消息网关
   */
  @Bean
  public GatewayProxyFactoryBean gateway() {
    GatewayProxyFactoryBean result = new GatewayProxyFactoryBean(MqttGateway.class);
    result.setDefaultRequestChannel(mqttOutboundChannel());
    return result;
  }

  /**
   * mqttTemplate的bean
   *
   * @param adapter mqtt适配器
   * @param gateway mqtt消息网关
   * @return mqttTemplate
   */
  @Bean
  @ConditionalOnMissingBean
  public MqttTemplate mqttTemplate(MqttAdapter adapter, MqttGateway gateway) {
    return new MqttTemplate(adapter, gateway);
  }
}
