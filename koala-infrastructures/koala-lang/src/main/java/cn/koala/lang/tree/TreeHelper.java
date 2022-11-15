package cn.koala.lang.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 树帮助类
 *
 * @author Houtaroy
 */
public final class TreeHelper {
  private TreeHelper() {
  }

  /**
   * 构建树
   *
   * @param nodes 树节点列表
   * @param <T>   树节点数据类型
   * @return 树
   */
  public static <T> List<TreeNode<T>> build(List<TreeNode<T>> nodes) {
    List<TreeNode<T>> result = new ArrayList<>();
    if (nodes.isEmpty()) {
      return result;
    }
    result = nodes.stream().filter(node -> node.getParentId() == null).collect(Collectors.toList());
    result.forEach(p -> p.setChildren(TreeHelper.build(nodes, p.getId())));
    return result;
  }

  /**
   * 构建指定上级id的树
   * 如果上级id为空, 则构建树
   *
   * @param nodes    树节点列表
   * @param parentId 上级id
   * @param <T>      树节点数据类型
   * @return 树
   */
  public static <T> List<TreeNode<T>> build(List<TreeNode<T>> nodes, Object parentId) {
    List<TreeNode<T>> result = new ArrayList<>();
    if (nodes.isEmpty()) {
      return result;
    }
    if (parentId == null) {
      return TreeHelper.build(nodes);
    }
    result = nodes.stream().filter(node -> parentId.equals(node.getParentId())).collect(Collectors.toList());
    result.forEach(p -> p.setChildren(TreeHelper.build(nodes, p.getId())));
    return result;
  }

  /**
   * 构建树
   *
   * @param data      列表数据
   * @param converter 树节点转换器
   * @param <T>       树节点数据类型
   * @return 树
   */
  public static <T> List<TreeNode<T>> build(List<T> data, Function<T, TreeNode<T>> converter) {
    if (data.isEmpty()) {
      return new ArrayList<>();
    }
    return build(data.stream().map(converter).toList());
  }
}