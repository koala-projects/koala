package cn.koala.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@MapperScan(basePackages = "cn.koala.system.repositories")
@SpringBootApplication
public class SampleSecurityApplication {
  /**
   * main方法
   *
   * @param args 参数
   */
  public static void main(String[] args) {
    SpringApplication.run(SampleSecurityApplication.class, args);
  }
}
