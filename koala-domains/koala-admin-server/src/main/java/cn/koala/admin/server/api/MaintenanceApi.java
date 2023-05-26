package cn.koala.admin.server.api;

import cn.koala.admin.server.Maintenance;
import cn.koala.admin.server.repository.MaintenanceRepository;
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
 * 运维关系管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@Tag(name = "运维关系管理")
public class MaintenanceApi {

  private final MaintenanceRepository repository;

  @Operation(operationId = "listMaintainers", summary = "查询运维关系列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceListResult.class))}
  )
  @GetMapping
  public Flux<DataResponse<List<Maintenance>>> list() {
    return repository.findAll().collectList().map(DataResponse::ok).flux();
  }

  @Operation(operationId = "loadMaintainer", summary = "根据id查询运维关系")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "运维关系id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  public Mono<DataResponse<Maintenance>> load(@PathVariable("id") String id) {
    return repository.findById(id).map(DataResponse::ok);
  }

  @Operation(operationId = "createMaintainer", summary = "创建运维关系")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceResult.class))}
  )
  @PostMapping
  public Mono<DataResponse<Maintenance>> create(@RequestBody Maintenance maintenance) {
    maintenance.setId(UUID.randomUUID().toString());
    return repository.save(maintenance).map(DataResponse::ok);
  }

  @Operation(operationId = "updateMaintainer", summary = "更新运维关系")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "运维关系id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  public Mono<DataResponse<Maintenance>> update(@PathVariable("id") String id, @RequestBody Maintenance maintenance) {
    maintenance.setId(id);
    return repository.save(maintenance).map(DataResponse::ok);
  }

  @DeleteMapping("{id}")
  public Mono<Response> delete(@PathVariable("id") String id) {
    return repository.deleteById(id).then(Mono.fromCallable(() -> Response.SUCCESS));
  }

  static class MaintenanceListResult extends DataResponse<List<Maintenance>> {

  }

  static class MaintenanceResult extends DataResponse<Maintenance> {

  }
}
