package ${packageName}.apis;

import ${packageName}.entites.${domain.code.capitalize}Entity
import ${packageName}.services.${domain.code.capitalize}Service

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Koala Eucalyptus
 */
@RequiredArgsConstructor
@RestController
public class ${domain.code.capitalize}ApiImpl implements ${domain.code.capitalize}Api {
  protected final ${domain.code.capitalize}Service service;

  @Override
  public DataResponse<Page<${domain.code.capitalize}Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }
  <#if domain.idProperty??>

  @Override
  public DataResponse<${domain.code.capitalize}Entity> loadById(${domain.idProperty.type} id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }
  </#if>

  @Override
  public DataResponse<${domain.code.capitalize}Entity> create(${domain.code.capitalize}Entity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }
  <#if domain.idProperty??>

  @Override
  public Response update(${domain.idProperty.type} id, ${domain.code.capitalize}Entity entity) {
    service.update(entity);
    return Response.ok("更新成功");
  }

  @Override
  public Response delete(${domain.idProperty.type} id) {
    service.delete(${domain.code.capitalize}Entity.builder().id(id).build());
    return Response.ok("删除成功");
  }
  </#if>
}
