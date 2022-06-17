package cn.koala.eucalyptus;

import cn.koala.utils.JdbcTable;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 领域接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/domains")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "domains", description = "领域接口")
public interface DomainApi {
  /**
   * 将表转换为领域模型
   *
   * @param table 表
   * @return 领域模型
   */
  @PostMapping
  DataResponse<JdbcDomain> create(@RequestBody JdbcTable table);

  /**
   * 根据领域上下文生成代码
   *
   * @param domainContext 领域上下文
   * @return 代码
   */
  @PostMapping("generate")
  DataResponse<Map<String, String>> generate(@RequestBody DefaultDomainContext domainContext);
}
