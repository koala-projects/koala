package cn.koala.sensitive.apis;

import cn.koala.sensitive.SensitiveWordFilter;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 敏感词接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class SensitiveWordApiImpl implements SensitiveWordApi {
  protected final SensitiveWordFilter filter;

  @Override
  public DataResponse<String> filter(SensitiveWordFilterRequest request) {
    return new DataResponse<>(
      HttpStatus.OK.value(),
      "请求成功",
      filter.filter(request.getContent(), request.getReplacement())
    );
  }
}
