package cn.koala.eucalyptus;

/**
 * 领域工厂
 *
 * @author Houtaroy
 */
public interface DomainFactory {
  /**
   * 根据参数生成领域模型
   *
   * @param data 数据
   * @return 领域模型
   */
  Domain create(Object data);
}
