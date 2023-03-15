package cn.koala.template.services;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;
import cn.koala.template.TemplateGroup;

/**
 * 模板组服务接口
 *
 * @author Houtaroy
 */
public interface TemplateGroupService extends CrudService<TemplateGroup, Long>, PagingService<TemplateGroup, Long> {

}
