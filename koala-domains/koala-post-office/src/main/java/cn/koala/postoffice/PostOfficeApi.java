package cn.koala.postoffice;

import cn.koala.web.DataRequest;
import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 驿站管理接口
 *
 * @author Houtaroy
 */
@RestController
@RequestMapping("/api/post-offices")
@Tag(name = "驿站管理")
@SecurityRequirement(name = "spring-security")
public interface PostOfficeApi {

  /**
   * 查询驿站列表
   *
   * @return 驿站列表
   */
  @PreAuthorize("hasAuthority('post-office:list')")
  @Operation(operationId = "listPostOffices", summary = "查询驿站列表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostOfficeListResult.class))}
  )
  @GetMapping
  DataResponse<List<PostOffice>> list();

  // @PreAuthorize("hasAuthority('post-office:post')")
  @Operation(operationId = "postOfficePost", summary = "驿站投递")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostApiResult.class))}
  )
  @PostMapping("post")
  DataResponse<List<PostResult>> post(@RequestBody DataRequest<List<PostRequest>> request);

  class PostOfficeListResult extends DataResponse<List<PostOffice>> {

  }

  class PostApiResult extends DataResponse<List<PostResult>> {

  }
}
