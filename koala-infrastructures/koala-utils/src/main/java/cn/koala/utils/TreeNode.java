package cn.koala.utils;

import lombok.Data;

import java.util.List;

/**
 * 树节点
 *
 * @param <T> 数据类型
 * @author Houtaroy
 */
@Data
public class TreeNode<T> {
  protected String id;
  protected String name;
  protected T data;
  protected String parentId;
  protected List<TreeNode<T>> children;

  /**
   * 构造函数
   *
   * @param id       节点id
   * @param data     节点数据
   * @param parentId 上级节点id
   */
  public TreeNode(String id, String name, String parentId, T data) {
    this.id = id;
    this.name = name;
    this.data = data;
    this.parentId = parentId;
  }
}
