package cn.koala.admin.server.api;

import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.strategy.NotifyStrategyService;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 通知策略接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/notify-strategies")
@RequiredArgsConstructor
@Tag(name = "通知策略管理")
public class NotifyStrategyApi {

  private final NotifyStrategyService service;

  @Operation(operationId = "listNotifyStrategies", summary = "查询通知策略列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotifyStrategyListResult.class))}
  )
  @GetMapping
  public Flux<DataResponse<List<String>>> list() {
    return Flux.just(DataResponse.ok(service.list().stream().map(NotifyStrategy::getName).toList()));
  }

  static class NotifyStrategyListResult extends DataResponse<List<String>> {

  }
}
