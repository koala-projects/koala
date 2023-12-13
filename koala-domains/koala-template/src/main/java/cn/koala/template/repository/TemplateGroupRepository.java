package cn.koala.template.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.template.domain.TemplateGroup;

/**
 * 模板组仓库接口
 *
 * @author Houtaroy
 */
public interface TemplateGroupRepository extends CrudRepository<TemplateGroup, Long> {
  
}
