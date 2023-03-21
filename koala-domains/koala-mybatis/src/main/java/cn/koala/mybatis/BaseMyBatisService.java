package cn.koala.mybatis;

import cn.koala.persist.BaseListenableCrudService;
import cn.koala.persist.CrudRepository;
import cn.koala.persist.listener.EntityListener;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * MyBatis基础服务类
 *
 * @author Houtaroy
 */
public class BaseMyBatisService<T, ID> extends BaseListenableCrudService<T, ID> {
  public BaseMyBatisService(CrudRepository<T, ID> repository) {
    super(repository);
  }

  public BaseMyBatisService(CrudRepository<T, ID> repository, List<EntityListener> listeners) {
    super(repository, listeners);
  }

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> repository.find(parameters));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }
}
