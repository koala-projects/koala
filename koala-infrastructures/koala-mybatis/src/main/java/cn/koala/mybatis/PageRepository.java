package cn.koala.mybatis;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
public interface PageRepository<T, E> extends CrudRepository<T, E> {
  /**
   * 查询全部
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据列表
   */
  List<E> findAll(@Param("parameters") Map<String, Object> parameters, Pageable pageable);
}
