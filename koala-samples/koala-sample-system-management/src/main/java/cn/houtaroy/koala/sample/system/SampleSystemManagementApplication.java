package cn.houtaroy.koala.sample.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@MapperScan(basePackages = "cn.koala.system.mybatis")
@SpringBootApplication
public class SampleSystemManagementApplication {

  /**
   * main方法
   *
   * @param args 参数
   */
  public static void main(String[] args) {
    SpringApplication.run(SampleSystemManagementApplication.class, args);
  }
}
