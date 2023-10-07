package #(package).api;

import #(package).entity.#(name)Entity;
import #(package).service.#(name)Service;

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
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class #(name)ApiImpl implements #(name)Api {
	
  protected final #(name)Service service;

  @Override
  public DataResponse<Page<#(name)Entity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<#(name)Entity> load(#(entity.properties.id.type) id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<#(name)Entity> create(#(name)Entity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(#(entity.properties.id.type) id, #(name)Entity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(#(entity.properties.id.type) id) {
    service.delete(#(name)Entity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
