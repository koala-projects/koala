package cn.koala.template.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.template.Template;

/**
 * @author Houtaroy
 */
public interface TemplateService extends CrudService<Long, Template>, PagingService<Long, Template> {
}
