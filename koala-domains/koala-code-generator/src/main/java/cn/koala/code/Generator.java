package cn.koala.code;

/**
 * 代码生成器
 *
 * @author Houtaroy
 */
public interface Generator<S, T> {
  T generate(S source);
}
