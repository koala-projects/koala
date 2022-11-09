package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Houtaroy
 */
@SpringBootApplication
@MapperScan("${package}.repositories")
public class Application {
  /**
   * main方法
   *
   * @param args 参数
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
