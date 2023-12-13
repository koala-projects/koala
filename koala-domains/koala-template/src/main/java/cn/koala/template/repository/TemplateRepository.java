package cn.koala.template.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.template.domain.Template;

/**
 * 模板仓库接口
 *
 * @author Houtaroy
 */
public interface TemplateRepository extends CrudRepository<Template, Long> {
}
