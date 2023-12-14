package cn.koala.mapping;

/**
 * 映射接口
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface Mapping<IN, OUT> {

  OUT map(IN input);
}
