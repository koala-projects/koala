package cn.koala.system.apis;

import cn.koala.system.DictionaryItem;
import cn.koala.system.entities.DictionaryItemEntity;
import cn.koala.system.services.DictionaryItemService;
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
public class DictionaryItemApiImpl implements DictionaryItemApi {
  protected final DictionaryItemService service;

  @Override
  public DataResponse<Page<DictionaryItem>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<DictionaryItem> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<DictionaryItem> add(DictionaryItemEntity entity) {
    service.save(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DictionaryItemEntity entity) {
    entity.setIdIfAbsent(id);
    service.save(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DictionaryItemEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
