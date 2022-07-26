package cn.koala.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(SystemManagementProperties.class)
@Import(BeanAutoConfig.class)
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemManagementAutoConfig {

}
