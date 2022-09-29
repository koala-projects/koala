package cn.koala.setting;

import cn.koala.datamodel.Data;
import cn.koala.datamodel.DataService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.DataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;

/**
 * 默认设置服务, 基于元数据和数据服务实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultSettingService implements SettingService {

  protected final DataService dataService;
  protected final MetadataService metadataService;

  @Override
  public Optional<Map<String, Object>> load(String id) {
    return findData(Map.of("metadataId", id)).map(Data::toMap);
  }

  @Override
  public Optional<Object> loadByKey(String key) {
    SettingKey settingKey = SettingHelper.parse(key);
    return findData(Map.of("metadataCode", settingKey.getMetadataCode()))
      .map(Data::toMap)
      .map(data -> data.get(settingKey.getPropertyCode()));
  }

  @Override
  public void add(Metadata settingDefinition, Map<String, Object> defaults) {
    metadataService.add(settingDefinition);
    Optional<Metadata> persistent = metadataService.load(settingDefinition.getId());
    Assert.isTrue(persistent.isPresent(), "创建失败, 设置定义保存异常");
    dataService.add(persistent.get(), defaults);
  }

  @Override
  public void update(String id, Map<String, Object> settings) {
    Optional<DataEntity> data = findData(Map.of("metadataId", id));
    Assert.isTrue(data.isPresent(), "更新失败, 设置不存在");
    dataService.update(data.get().getId(), settings);
  }

  protected Optional<DataEntity> findData(Map<String, Object> parameters) {
    return Optional.ofNullable(dataService.list(parameters))
      .filter(data -> data.size() == 1)
      .map(data -> data.get(0))
      .filter(data -> data instanceof DataEntity)
      .map(DataEntity.class::cast);
  }
}
