package cn.koala.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 数字工具类
 *
 * @author Houtaroy
 */
public abstract class NumberUtils extends org.springframework.util.NumberUtils {

  private static final Integer INT_0 = 0;
  private static final Integer INT_1 = 1;

  public static List<Integer> range(int end) {
    return range(INT_0, end, INT_1);
  }

  public static List<Integer> range(int start, int end) {
    return range(start, end, INT_1);
  }

  public static List<Integer> range(int start, int end, int step) {
    return IntStream.iterate(start, i -> i < end, i -> i + step).boxed().collect(Collectors.toList());
  }
}
