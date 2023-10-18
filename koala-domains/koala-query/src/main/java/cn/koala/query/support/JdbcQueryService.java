package cn.koala.query.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.query.Query;
import cn.koala.query.QueryService;
import cn.koala.query.repository.QueryRepository;
import cn.koala.web.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 基于JdbcTemplate的查询服务实现类
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class JdbcQueryService extends AbstractMyBatisService<Query, Long> implements QueryService {

  private final QueryRepository repository;
  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Page<Map<String, Object>> execute(Long id, Map<String, Object> parameters, Pageable pageable) {
    Query query = getRepository().load(id).orElseThrow(() -> new BusinessException("查询不存在"));
    addPageableParameters(parameters, pageable);
    resetNullParameters(parameters);
    List<Map<String, Object>> result = jdbcTemplate.queryForList(query.getSql(), parameters);
    return new PageImpl<>(result, pageable, result.size());
  }

  private void addPageableParameters(Map<String, Object> parameters, Pageable pageable) {
    parameters.put("page", pageable.getPageNumber());
    parameters.put("size", pageable.getPageSize());
    parameters.put("offset", pageable.getOffset());
  }

  private void resetNullParameters(Map<String, Object> parameters) {
    parameters.forEach((key, value) -> {
      if (value instanceof String str && !StringUtils.hasText(str)) {
        parameters.put(key, null);
      }
    });
  }
}
