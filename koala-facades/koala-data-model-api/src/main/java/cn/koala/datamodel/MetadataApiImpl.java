package cn.koala.datamodel;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 元数据接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class MetadataApiImpl implements MetadataApi {

  protected final MetadataService metadataService;

  @Override
  public DataResponse<Page<Metadata>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(metadataService.list(parameters, pageable));
  }

  @Override
  public DataResponse<Metadata> loadById(String id) {
    return DataResponse.ok(metadataService.load(id).orElse(null));
  }

  @Override
  public DataResponse<Metadata> create(PersistentMetadata metadata) {
    metadataService.add(metadata);
    return DataResponse.ok(metadata);
  }

  @Override
  public Response delete(String id) {
    metadataService.delete(PersistentMetadata.builder().id(id).build());
    return Response.SUCCESS;
  }
}
