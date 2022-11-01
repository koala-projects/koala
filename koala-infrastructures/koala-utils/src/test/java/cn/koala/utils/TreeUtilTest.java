package cn.koala.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Houtaroy
 */
public class TreeUtilTest {
  @Data
  @AllArgsConstructor
  static class Department {
    private String id;
    private String name;
    private String parentId;
  }

  @Test
  public void test() {
    List<Department> departments = List.of(
      new Department("1", "技术部", null),
      new Department("2", "综合部", null),
      new Department("1-1", "软件部", "1"),
      new Department("1-2", "集成部", "1")
    );
    List<TreeNode<Department>> tree = TreeUtil.build(
      departments,
      department -> new TreeNode<>(department.getId(), department.getName(), department.getParentId(), department)
    );
    Assertions.assertEquals(tree.size(), 2);
    Assertions.assertEquals(tree.get(1).getData().getName(), "综合部");
    Assertions.assertEquals(tree.get(0).getChildren().size(), 2);
    Assertions.assertEquals(tree.get(0).getChildren().get(0).getData().getName(), "软件部");
  }
}
