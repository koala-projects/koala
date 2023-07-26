package cn.koala.query;

import cn.koala.persist.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 查询服务接口
 *
 * @author Houtaroy
 */
public interface QueryService extends CrudService<Query, Long> {

  Page<Map<String, Object>> execute(Long id, Map<String, Object> parameters, Pageable pageable);
}
