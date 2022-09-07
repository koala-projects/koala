package cn.koala.mybatis;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 分页增强工具类
 *
 * @author Houtaroy
 */
public abstract class PageEnhancedHelper {

  /**
   * 分页查询
   *
   * @param select   分页查询接口
   * @param pageable 分页对象
   * @param <T>      结果类型
   * @return 分页查询结果
   */
  public static <T> Page<T> page(ISelect select, Pageable pageable) {
    com.github.pagehelper.Page<T> result = PageHelper.startPage(
      Math.max(pageable.getPageNumber() + 1, 1),
      pageable.getPageSize()).doSelectPage(select
    );
    return new PageImpl<>(result, pageable, result.getTotal());
  }
}
