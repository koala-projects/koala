package cn.koala.system.setting;

import cn.koala.data.service.CrudService;

/**
 * 设置服务接口
 *
 * @author Houtaroy
 */
public interface SettingService extends CrudService<Setting, Long> {

  <T> T getSetting(String code);
}
