package cn.koala.mybatis.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 增删改查仓库接口
 *
 * @param <T>  数据实体类型
 * @param <ID> 主键类型
 * @author Houtaroy
 */
@NoRepositoryBean
public interface CrudRepository<T, ID> {

  List<T> list(Map<String, Object> parameters);

  Optional<T> load(ID id);

  <S extends T> void create(S entity);

  <S extends T> void update(S entity);

  <S extends T> void delete(S entity);
}
