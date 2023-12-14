package #(package).api;

import #(package).entity.#(name.pascal.singular)Entity;
import #(package).service.#(name.pascal.singular)Service;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * #(description)接口实现类
 *
 * @author Koala Code Gen
 */
@RestController
@RequiredArgsConstructor
public class #(name.pascal.singular)ApiImpl implements #(name.pascal.singular)Api {
	
  private final #(name.pascal.singular)Service service;

  @Override
  public DataResponse<Page<#(name.pascal.singular)Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<#(name.pascal.singular)Entity> load(#(id.type.java) id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<#(name.pascal.singular)Entity> create(#(name.pascal.singular)Entity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(id.type.java) id, #(name.pascal.singular)Entity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(id.type.java) id) {
    service.delete(#(name.pascal.singular)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
