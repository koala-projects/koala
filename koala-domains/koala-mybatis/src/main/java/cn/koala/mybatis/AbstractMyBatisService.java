package cn.koala.mybatis;

import cn.koala.persist.CrudRepository;
import cn.koala.persist.support.AbstractCrudService;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * MyBatis基础服务类
 *
 * @author Houtaroy
 */
public class AbstractMyBatisService<T, ID> extends AbstractCrudService<T, ID> {

  public AbstractMyBatisService(CrudRepository<T, ID> repository) {
    super(repository);
  }

  @Override
  public Page<T> read(Map<String, Object> parameters, Pageable pageable) {
    parameters.put("orders", pageable.getSort().toList());
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> repository.find(parameters));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }
}
