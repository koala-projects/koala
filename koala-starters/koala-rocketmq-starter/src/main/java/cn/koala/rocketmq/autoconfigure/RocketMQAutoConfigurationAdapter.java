package cn.koala.rocketmq.autoconfigure;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * RocketMQ自动配置适配器
 * <p>
 * 因RocketMQ Spring Boot Starter暂时不支持Spring Boot 3的自定装配, 特撰写此适配器, 将在其支持后废弃
 *
 * @author Houtaroy
 */
@Configuration
@Import(RocketMQAutoConfiguration.class)
public class RocketMQAutoConfigurationAdapter {
}
