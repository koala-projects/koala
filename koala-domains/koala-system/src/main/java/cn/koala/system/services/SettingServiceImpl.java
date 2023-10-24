package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.Setting;
import cn.koala.system.repository.SettingRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 设置服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class SettingServiceImpl extends AbstractMyBatisService<Setting, Long> implements SettingService {

  protected final SettingRepository repository;
}
