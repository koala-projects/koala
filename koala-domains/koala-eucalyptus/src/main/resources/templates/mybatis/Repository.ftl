package ${packageName}.repositories;

import ${packageName}.entities.${domain.code.capitalize}Entity;

/**
 * ${domain.name}存储库类
 *
 * @author Koala Eucalyptus
 */
public interface ${domain.code.capitalize}Repository {
  /**
  * 查询全部
  *
  * @param parameters 查询参数
  * @param pageable   分页参数
  * @return 数据列表
  */
  List<${domain.code.capitalize}Entity> findAll(@Param("parameters") Map<String, Object> parameters, Pageable pageable);
  <#if domain.idProperty??>

  /**
  * 根据id查询
  *
  * @param id id
  * @return 数据实体
  */
  Optional<${domain.code.capitalize}Entity> findById(${domain.idProperty.type} id);
  </#if>

  /**
  * 新增
  *
  * @param entity 数据实体
  */
  void add(${domain.code.capitalize}Entity entity);

  /**
  * 更新
  *
  * @param entity 数据实体
  */
  void update(${domain.code.capitalize}Entity entity);

  /**
  * 删除
  *
  * @param entity 数据实体
  */
  void delete(${domain.code.capitalize}Entity entity);
}