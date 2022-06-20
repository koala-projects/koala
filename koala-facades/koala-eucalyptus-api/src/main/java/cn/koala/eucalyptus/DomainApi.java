package cn.koala.eucalyptus;

import cn.koala.utils.JdbcTable;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 领域接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/domains")
@RestController
@Tag(name = "domains", description = "领域接口")
public interface DomainApi {
  /**
   * 将表转换为领域模型
   *
   * @param table 表
   * @return 领域模型
   */
  @PostMapping
  DataResponse<Domain> create(@RequestBody JdbcTable table);
}
