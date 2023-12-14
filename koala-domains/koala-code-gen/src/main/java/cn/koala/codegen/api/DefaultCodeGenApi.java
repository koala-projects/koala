package cn.koala.codegen.api;


import cn.koala.codegen.service.CodeGenService;
import cn.koala.codegen.service.MultiCodeGenResult;
import cn.koala.database.service.DatabaseService;
import cn.koala.exception.BusinessException;
import cn.koala.template.service.TemplateService;
import cn.koala.util.CompressUtils;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 默认代码生成接口
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DefaultCodeGenApi implements CodeGenApi {

  private final DatabaseService databaseService;
  private final TemplateService templateService;
  private final CodeGenService codeGenService;
  private final String codeGenPath;

  @Override
  public DataResponse<List<MultiCodeGenResult>> preview(CodeGenRequest request) {
    var tables = databaseService.listTable(request.getDatabaseId(), request.getTables());
    var templates = templateService.list(Map.of("groupId", request.getTemplateGroupId()));
    return DataResponse.ok(codeGenService.generate(tables, templates));
  }

  @Override
  public DataResponse<String> download(CodeGenRequest request) {
    var tables = databaseService.listTable(request.getDatabaseId(), request.getTables());
    var templates = templateService.list(Map.of("groupId", request.getTemplateGroupId()));
    var multiResults = codeGenService.generate(tables, templates);
    var dir = new File(codeGenPath + File.separator + UUID.randomUUID());
    multiResults.forEach(multiResult -> multiResult.getCodeGenResults().forEach(result -> {
      try {
        FileUtils.write(
          new File(dir.getPath() + File.separator + result.getFilename()),
          result.getContent(),
          Charset.defaultCharset()
        );
      } catch (IOException e) {
        throw new BusinessException("生成代码文件[filename=%s]失败".formatted(result.getFilename()));
      }
    }));
    String filename = dir.getName() + "." + ArchiveStreamFactory.ZIP;
    try {
      CompressUtils.compress(dir, new File(codeGenPath + filename), ArchiveStreamFactory.ZIP);
    } catch (IOException | ArchiveException e) {
      throw new BusinessException("生成代码文件压缩包失败");
    }
    return new DataResponse<>(HttpStatus.OK.value(), "请求成功", "/api/code-gen/file/%s".formatted(filename));
  }
}
