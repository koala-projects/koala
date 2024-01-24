package cn.koala.system.setting;

/**
 * 设置值解析策略接口
 *
 * @author Houtaroy
 */
public interface SettingValueParseStrategy<T> {

  SettingType getSettingType();

  T parse(String value);
}
