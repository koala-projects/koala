package cn.koala.setting;

import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 设置定义接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class SettingDefinitionApiImpl implements SettingDefinitionApi {

  protected final MetadataService metadataService;

  @Override
  public DataResponse<List<PersistentMetadata>> list(Map<String, Object> parameters) {
    return DataResponse.ok(metadataService.list(parameters));
  }
}
