package cn.koala.data.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Houtaroy
 */
public abstract class PageUtils {

  public static <T> Page<T> paginate(List<T> entities, Pageable pageable) {
    int pageNumber = Math.max(pageable.getPageNumber() + 1, 1);
    int startIndex = (pageNumber - 1) * pageable.getPageSize();
    int endIndex = Math.min(startIndex + pageable.getPageSize() - 1, entities.size());
    return new PageImpl<>(entities.subList(startIndex, endIndex), pageable, entities.size());
  }
}
