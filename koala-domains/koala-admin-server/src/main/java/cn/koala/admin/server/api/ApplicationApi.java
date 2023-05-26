package cn.koala.admin.server.api;

import cn.koala.admin.server.Application;
import cn.koala.admin.server.repository.ApplicationRepository;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * 应用管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(name = "应用管理")
public class ApplicationApi {

  private final ApplicationRepository repository;

  @Operation(operationId = "listApplications", summary = "查询应用列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationListResult.class))}
  )
  @GetMapping
  public Flux<DataResponse<List<Application>>> list() {
    return repository.findAll().collectList().map(DataResponse::ok).flux();
  }

  @Operation(operationId = "loadApplication", summary = "根据id查询应用")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "应用id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  public Mono<DataResponse<Application>> load(@PathVariable("id") String id) {
    return repository.findById(id).map(DataResponse::ok);
  }

  @Operation(operationId = "createApplication", summary = "创建应用")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationResult.class))}
  )
  @PostMapping
  public Mono<DataResponse<Application>> create(@RequestBody Application application) {
    application.setId(UUID.randomUUID().toString());
    return repository.save(application).map(DataResponse::ok);
  }

  @Operation(operationId = "updateApplication", summary = "更新应用")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "应用id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  public Mono<DataResponse<Application>> update(@PathVariable("id") String id, @RequestBody Application application) {
    application.setId(id);
    return repository.save(application).map(DataResponse::ok);
  }

  @Operation(operationId = "deleteApplication", summary = "删除应用")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "应用id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  public Mono<Response> delete(@PathVariable("id") String id) {
    return repository.deleteById(id).then(Mono.fromCallable(() -> Response.SUCCESS));
  }

  static class ApplicationListResult extends DataResponse<List<Application>> {

  }

  static class ApplicationResult extends DataResponse<Application> {

  }
}
