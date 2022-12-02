package cn.koala.builder;

import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 领域转换器接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/domain-converters")
@RestController
@Tag(name = "领域转换器管理")
public interface DomainConverterApi {

  /**
   * 查询领域转换器
   *
   * @return 领域转换器列表
   */
  @Operation(summary = "查询领域转换器", tags = {"domainConverter"})
  @ApiResponse(
    responseCode = "200", description = "成功",
    content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = DomainConverterListResult.class))
    }
  )
  @GetMapping
  DataResponse<List<DomainConverter>> list();

  class DomainConverterListResult extends DataResponse<List<DomainConverter>> {

  }
}
