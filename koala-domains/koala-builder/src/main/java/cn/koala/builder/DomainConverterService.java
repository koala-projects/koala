package cn.koala.builder;

import java.util.Optional;

/**
 * 领域转换器服务
 *
 * @author Houtaroy
 */
public interface DomainConverterService {
  /**
   * 根据ID获取领域转换器
   *
   * @param id ID
   * @return 领域转换器
   */
  Optional<DomainConverter> load(String id);

  /**
   * 新增领域转换器
   *
   * @param domainConverter 领域转换器对象
   */
  void add(DomainConverter domainConverter);

  /**
   * 移除领域转换器
   *
   * @param id 领域转换器标识
   */
  void delete(String id);
}
