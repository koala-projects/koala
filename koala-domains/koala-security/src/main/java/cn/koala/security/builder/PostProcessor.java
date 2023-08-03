package cn.koala.security.builder;

/**
 * 附加处理器接口
 *
 * @author Houtaroy
 */
public interface PostProcessor<T> {

  void postProcessBeforeBuild(T object) throws Exception;

  void postProcessAfterBuild(T object) throws Exception;
}
