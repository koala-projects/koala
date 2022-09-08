package cn.koala.setting;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 设置接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class SettingApiImpl implements SettingApi {

  protected final SettingService settingService;

  @Override
  public DataResponse<Map<String, Object>> loadById(String id) {
    return DataResponse.ok(settingService.load(id).orElse(null));
  }

  @Override
  public Response update(String id, Map<String, Object> settings) {
    settingService.update(id, settings);
    return Response.SUCCESS;
  }
}
