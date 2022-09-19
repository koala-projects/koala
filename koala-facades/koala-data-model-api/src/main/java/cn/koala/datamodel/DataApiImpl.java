package cn.koala.datamodel;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DataApiImpl implements DataApi {

  protected final DataService dataService;

  @Override
  public DataResponse<Page<Map<String, Object>>> page(Map<String, Object> parameters, Pageable pageable) {
    Page<Data> data = dataService.list(parameters, pageable);
    List<Map<String, Object>> result = data.getContent().stream()
      .map(Data::toMap)
      .toList();
    return DataResponse.ok(new PageImpl<>(result, pageable, data.getTotalPages()));
  }

  @Override
  public DataResponse<Map<String, Object>> loadById(String id) {
    return DataResponse.ok(dataService.load(id).map(Data::toMap).orElse(null));
  }

  @Override
  public Response create(CreateDataRequest createDataRequest) {
    dataService.add(createDataRequest.getMetadata(), createDataRequest.getContents());
    return Response.SUCCESS;
  }

  @Override
  public Response update(String id, Map<String, Object> data) {
    dataService.update(id, data);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    dataService.delete(id);
    return Response.SUCCESS;
  }
}
