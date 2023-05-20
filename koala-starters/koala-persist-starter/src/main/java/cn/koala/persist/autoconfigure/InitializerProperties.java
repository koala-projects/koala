package cn.koala.persist.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;

/**
 * 初始化器配置类
 *
 * @author Houtaroy
 */
@ConfigurationProperties("koala.persist.initializer")
public class InitializerProperties extends HashMap<String, Boolean> {

}
