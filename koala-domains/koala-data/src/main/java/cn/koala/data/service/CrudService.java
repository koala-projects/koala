package cn.koala.data.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服务接口
 *
 * @param <T>  实体类型
 * @param <ID> 主键类型
 * @author Houtaroy
 */
public interface CrudService<T, ID> {

  Page<T> page(Map<String, Object> parameters, Pageable pageable);

  List<T> list(Map<String, Object> parameters);

  T load(ID id);

  <S extends T> void create(@NonNull S entity);

  <S extends T> void update(@NonNull S entity);

  <S extends T> void delete(@NonNull S entity);
}
