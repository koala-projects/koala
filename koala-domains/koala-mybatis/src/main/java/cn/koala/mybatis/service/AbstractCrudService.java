package cn.koala.mybatis.service;

import cn.koala.data.service.CrudService;
import cn.koala.mybatis.repository.CrudRepository;
import com.github.pagehelper.PageHelper;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服务抽象类
 * <p>
 * 基于{@link CrudRepository}实现
 *
 * @author Houtaroy
 */
public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

  protected abstract CrudRepository<T, ID> getRepository();

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    parameters.put("orders", pageable.getSort().toList());
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> getRepository().list(parameters));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return getRepository().list(parameters);
  }

  @Override
  public T load(ID id) {
    return getRepository().load(id).orElse(null);
  }

  @Override
  public <S extends T> void create(@NonNull S entity) {
    getRepository().create(entity);
  }

  @Override
  public <S extends T> void update(@NonNull S entity) {
    getRepository().update(entity);
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    getRepository().delete(entity);
  }
}
