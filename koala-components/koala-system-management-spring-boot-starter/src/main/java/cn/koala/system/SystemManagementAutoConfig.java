package cn.koala.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Houtaroy
 */
@Configuration
@Import({ApiConfig.class, ServiceConfig.class})
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemManagementAutoConfig {

}
