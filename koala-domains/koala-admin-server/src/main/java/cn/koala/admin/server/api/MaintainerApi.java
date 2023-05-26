package cn.koala.admin.server.api;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.repository.MaintainerRepository;
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
 * 运维工程师管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/maintainers")
@RequiredArgsConstructor
@Tag(name = "运维工程师管理")
public class MaintainerApi {

  private final MaintainerRepository repository;

  @Operation(operationId = "listMaintainers", summary = "查询运维工程师列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintainerListResult.class))}
  )
  @GetMapping
  public Flux<DataResponse<List<Maintainer>>> list() {
    return repository.findAll().collectList().map(DataResponse::ok).flux();
  }

  @Operation(operationId = "loadMaintainer", summary = "根据id查询运维工程师")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintainerResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "运维工程师id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  public Mono<DataResponse<Maintainer>> load(@PathVariable("id") String id) {
    return repository.findById(id).map(DataResponse::ok);
  }

  @Operation(operationId = "createMaintainer", summary = "创建运维工程师")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintainerResult.class))}
  )
  @PostMapping
  public Mono<DataResponse<Maintainer>> create(@RequestBody Maintainer maintainer) {
    maintainer.setId(UUID.randomUUID().toString());
    return repository.save(maintainer).map(DataResponse::ok);
  }

  @Operation(operationId = "updateMaintainer", summary = "更新运维工程师")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintainerResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "运维工程师id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  public Mono<DataResponse<Maintainer>> update(@PathVariable("id") String id, @RequestBody Maintainer maintainer) {
    maintainer.setId(id);
    return repository.save(maintainer).map(DataResponse::ok);
  }

  @Operation(operationId = "deleteMaintainer", summary = "删除运维工程师")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "运维工程师id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  public Mono<Response> delete(@PathVariable("id") String id) {
    return repository.deleteById(id).then(Mono.fromCallable(() -> Response.SUCCESS));
  }

  static class MaintainerListResult extends DataResponse<List<Maintainer>> {

  }

  static class MaintainerResult extends DataResponse<Maintainer> {

  }
}
