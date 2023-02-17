package cn.koala.system.apis;

import cn.koala.system.Dictionary;
import cn.koala.system.entities.DictionaryEntity;
import cn.koala.system.services.DictionaryService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DictionaryApiImpl implements DictionaryApi {
  protected final DictionaryService service;

  @Override
  public DataResponse<Page<Dictionary>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Dictionary> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Dictionary> add(DictionaryEntity entity) {
    service.save(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DictionaryEntity entity) {
    entity.setIdIfAbsent(id);
    service.save(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DictionaryEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
