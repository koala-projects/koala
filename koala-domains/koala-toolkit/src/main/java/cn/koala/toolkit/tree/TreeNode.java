package cn.koala.toolkit.tree;

import lombok.Data;

import java.util.List;

/**
 * 树节点
 *
 * @author Houtaroy
 */
@Data
public class TreeNode {
  protected Object id;
  protected String name;
  protected Object data;
  protected Object parentId;
  protected List<TreeNode> children;

  /**
   * 构造函数
   *
   * @param id       节点id
   * @param data     节点数据
   * @param parentId 上级节点id
   */
  public TreeNode(Object id, String name, Object parentId, Object data) {
    this.id = id;
    this.name = name;
    this.data = data;
    this.parentId = parentId;
  }
}
