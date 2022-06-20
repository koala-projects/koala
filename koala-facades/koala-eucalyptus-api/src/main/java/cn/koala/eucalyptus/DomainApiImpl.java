package cn.koala.eucalyptus;

import cn.koala.utils.JdbcTable;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DomainApiImpl implements DomainApi {
  private final DomainFactory domainFactory;

  @Override
  public DataResponse<Domain> create(JdbcTable table) {
    return DataResponse.ok(domainFactory.create(table));
  }
}
