package cn.koala.mybatis;

import cn.koala.persistence.Idable;

import java.util.function.Function;

/**
 * 主键生成器
 *
 * @param <E> 数据实体类型
 * @param <K> 主键类型
 * @author Houtaroy
 */
public interface IdGenerator<E extends Idable<K>, K> extends Function<E, K> {
}
