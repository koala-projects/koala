package cn.koala.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathHelperTest {

  @AllArgsConstructor
  @Getter
  static class Count {
    private String name;
    private Integer value;
  }

  @Test
  public void distribution() {
    List<Count> data = new ArrayList<>();
    data.add(new Count("zip/one", 1000));
    data.add(new Count("two", 10));
    data.add(new Count("three", 1));
    Map<String, Integer> result = new HashMap<>();
    for (int i = 0; i < 1000; i++) {
      Count test = MathHelper.distribution(data, item -> item.getValue().doubleValue());
      int count = result.getOrDefault(test.getName(), 0);
      result.put(test.getName(), ++count);
    }
    Assertions.assertTrue(result.get("zip/one") > result.get("two"));
    Assertions.assertTrue(result.get("two") >= result.getOrDefault("three", 0));
  }
}
