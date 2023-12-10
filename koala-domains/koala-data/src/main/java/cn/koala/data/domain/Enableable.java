package cn.koala.data.domain;

/**
 * 可启用的实体接口
 *
 * @author Houtaroy
 */
public interface Enableable {

  YesNo getEnabled();

  void setEnabled(YesNo enabled);
}
