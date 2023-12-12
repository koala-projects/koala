package cn.koala.mybatis.autoconfigure;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableTransactionManagement
@MapperScan("cn.koala.mybatis.common")
public class MyBatisAutoConfiguration {
  
}
