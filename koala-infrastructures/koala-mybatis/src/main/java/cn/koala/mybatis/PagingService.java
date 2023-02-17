package cn.koala.mybatis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 分页服务接口
 *
 * @author Houtaroy
 */
public interface PagingService<T, ID> {
  /**
   * 分页查询数据
   *
   * @param parameters 查询参数
   * @param pageable   分页参数
   * @return 数据分页列表
   */
  Page<T> page(Map<String, Object> parameters, Pageable pageable);
}
