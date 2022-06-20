package cn.koala.eucalyptus;

import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 领域接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/templates")
@RestController
@Tag(name = "templates", description = "代码模板接口")
public interface TemplateApi {
  /**
   * 获取模板列表
   *
   * @return 模板列表
   */
  @GetMapping
  DataResponse<List<Template>> list();

  /**
   * 使用指定代码模板, 根据领域上下文生成代码
   *
   * @param code          模板代码
   * @param domainContext 领域上下文
   * @return 代码
   */
  @PostMapping("{code}/generate")
  DataResponse<Map<String, String>> generate(@PathVariable("code") String code,
                                             @RequestBody DefaultDomainContext domainContext);
}
