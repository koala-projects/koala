package cn.koala.system.setting;

import cn.koala.data.util.DomainUtils;
import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.repository.SettingRepository;
import cn.koala.util.Assert;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 设置服务类
 *
 * @author Koala Code Gen
 */
@Component
@Getter
@RequiredArgsConstructor
public class DefaultSettingService extends AbstractSmartService<Setting, Long> implements SettingService {

  private final SettingRepository repository;
  private final Map<SettingType, SettingValueParseStrategy<?>> settingValueParseStrategies;

  public DefaultSettingService(SettingRepository repository,
                               List<SettingValueParseStrategy<?>> settingValueParseStrategies) {

    this.repository = repository;
    this.settingValueParseStrategies = settingValueParseStrategies.stream()
      .collect(Collectors.toMap(SettingValueParseStrategy::getSettingType, strategy -> strategy));
  }

  @Override
  public <S extends Setting> void update(@NonNull S entity) {
    Setting persistence = load(entity.getId());
    Assert.isTrue(!DomainUtils.isSystemic(persistence), "系统数据不允许修改");
    persistence.setValue(entity.getValue());
    getRepository().update(persistence);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T getSetting(String code) {
    List<Setting> settings = list(Map.of("code", code));
    Assert.isTrue(!settings.isEmpty(), "设置不存在");
    Setting setting = settings.get(0);
    SettingValueParseStrategy<?> strategy = settingValueParseStrategies.get(setting.getType());
    Assert.notNull(strategy, "设置类型异常, 无法解析设置值");
    return (T) strategy.parse(setting.getValue());
  }
}
