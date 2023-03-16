package cn.koala.template.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.template.Template;

/**
 * 模板服务接口
 *
 * @author Houtaroy
 */
public interface TemplateService extends CrudService<Template, Long>, PagingService<Template, Long> {
}
