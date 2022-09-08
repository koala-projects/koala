package cn.koala.setting;

import cn.koala.datamodel.PersistentMetadata;

import java.util.List;
import java.util.Map;

/**
 * 设置定义服务
 *
 * @author Houtaroy
 */
public interface SettingDefinitionService {
  /**
   * 查询设置定义
   *
   * @param parameters 查询参数
   * @return 设置定义列表
   */
  List<PersistentMetadata> list(Map<String, Object> parameters);
}
