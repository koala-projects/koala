package cn.koala.mybatis;

import cn.koala.persist.support.AbstractCrudService;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * MyBatis基础服务类
 * <p>
 * 使用<a href="https://github.com/pagehelper/Mybatis-PageHelper">PageHelper</a>重写分页方法
 *
 * @author Houtaroy
 */
@Deprecated
public abstract class AbstractMyBatisService<T, ID> extends AbstractCrudService<T, ID> {

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    parameters.put("orders", pageable.getSort().toList());
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> getRepository().list(parameters));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }
}
