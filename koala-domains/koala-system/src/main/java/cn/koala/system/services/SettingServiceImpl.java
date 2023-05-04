package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.Setting;
import cn.koala.system.repositories.SettingRepository;

/**
 * 设置服务实现类
 *
 * @author Houtaroy
 */
public class SettingServiceImpl extends AbstractMyBatisService<Setting, Long> implements SettingService {
  public SettingServiceImpl(SettingRepository repository) {
    super(repository);
  }
}
