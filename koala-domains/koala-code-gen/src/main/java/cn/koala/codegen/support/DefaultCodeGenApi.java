package cn.koala.codegen.support;

import cn.koala.codegen.CodeGenApi;
import cn.koala.codegen.CodeGenResult;
import cn.koala.codegen.CodeGenService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 代码接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DefaultCodeGenApi implements CodeGenApi {

  protected final CodeGenService codeService;

  @Override
  public DataResponse<Map<String, List<CodeGenResult>>> preview(CodeGenRequest request) {
    return DataResponse.ok(
      codeService.preview(request.getDatabaseId(), request.getTables(), request.getTemplateGroupId())
    );
  }

  @Override
  public DataResponse<String> download(CodeGenRequest request) {
    String url = codeService.download(request.getDatabaseId(), request.getTables(), request.getTemplateGroupId());
    return new DataResponse<>(HttpStatus.OK.value(), "请求成功", "/api/code/download/%s".formatted(url));
  }
}
