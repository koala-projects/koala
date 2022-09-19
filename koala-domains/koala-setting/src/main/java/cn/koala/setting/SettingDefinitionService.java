package cn.koala.setting;

import cn.koala.datamodel.Metadata;

import java.util.List;
import java.util.Map;

/**
 * 设置定义服务类
 *
 * @author Houtaroy
 */
public interface SettingDefinitionService {
  /**
   * 查询设置定义列表
   *
   * @param parameters 查询参数
   * @return 设置定义列表
   */
  List<Metadata> list(Map<String, Object> parameters);
}
