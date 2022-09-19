package cn.koala.setting;

import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 默认设置定义服务, 基于元数据服务实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultSettingDefinitionService implements SettingDefinitionService {

  protected final MetadataService metadataService;

  @Override
  public List<Metadata> list(Map<String, Object> parameters) {
    return metadataService.list(parameters);
  }
}
