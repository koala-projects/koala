package cn.koala.security;

import cn.koala.web.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息接口
 *
 * @author Houtaroy
 */
@RequestMapping("/api/userinfo")
@RestController
@Tag(name = "用户信息")
public interface UserinfoApi {
  /**
   * 根据id查询用户
   *
   * @return 用户详细信息
   */
  @Operation(summary = "查询当前用户信息")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserinfoResult.class))}
  )
  @GetMapping
  DataResponse<UserDetails> userinfo();

  @Schema(description = "用户信息结果")
  class UserinfoResult extends DataResponse<UserDetailsImpl> {

  }
}
