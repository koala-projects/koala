package cn.koala.system.api;

import cn.koala.log.annotation.Log;
import cn.koala.system.domain.DictionaryItem;
import cn.koala.system.domain.DictionaryItemEntity;
import cn.koala.system.service.DictionaryItemService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典项接口实现
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DefaultDictionaryItemApi implements DictionaryItemApi {

  private final DictionaryItemService service;

  @Override
  @Log(module = "字典项管理", content = "查询字典项列表")
  public DataResponse<Page<DictionaryItem>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  @Log(module = "字典项管理", content = "查看字典项[id=${#id}]")
  public DataResponse<DictionaryItem> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  @Log(module = "字典项管理", content = "创建字典项[code=${#entity.code}]")
  public DataResponse<DictionaryItem> create(DictionaryItemEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "字典项管理", content = "更新字典项[id=${#id}]")
  public Response update(Long id, DictionaryItemEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "字典项管理", content = "删除字典项[id=${#id}]")
  public Response delete(Long id) {
    service.delete(DictionaryItemEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
