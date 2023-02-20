package cn.koala.system.services;

import cn.koala.system.Setting;
import cn.koala.system.repositories.SettingRepository;

/**
 * 设置服务实现类
 *
 * @author Houtaroy
 */
public class SettingServiceImpl extends BaseSystemService<Setting> implements SettingService {
  public SettingServiceImpl(SettingRepository repository) {
    super(repository);
  }
}
