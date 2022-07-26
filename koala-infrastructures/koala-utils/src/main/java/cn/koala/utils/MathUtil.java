package cn.koala.utils;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 数学工具类
 *
 * @author Houtaroy
 */
public final class MathUtil {

  private MathUtil() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 概率分布
   *
   * @param data            数据列表
   * @param probabilityFunc 获取概率函数
   * @param <T>             数据类型
   * @return 概率分布结果
   */
  public static <T> T distribution(List<T> data, Function<T, Double> probabilityFunc) {
    List<Pair<T, Double>> pairs = data.stream().map(item -> new Pair<>(item, probabilityFunc.apply(item)))
      .collect(Collectors.toList());
    return new EnumeratedDistribution<>(pairs).sample();
  }
}
