package cn.koala.postoffice;

import java.util.Map;

/**
 * 打包员, 负责将原始内容打包为指定类型的包裹
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface Packer<T> {

  T pack(Map<String, Object> original) throws Exception;
}
