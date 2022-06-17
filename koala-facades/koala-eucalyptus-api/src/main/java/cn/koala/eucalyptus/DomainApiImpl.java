package cn.koala.eucalyptus;

import cn.koala.utils.JdbcTable;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DomainApiImpl implements DomainApi {
  @Override
  public DataResponse<JdbcDomain> create(JdbcTable table) {
    return null;
  }

  @Override
  public DataResponse<Map<String, String>> generate(DefaultDomainContext domainContext) {
    return null;
  }
}
