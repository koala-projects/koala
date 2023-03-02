package cn.koala.security;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@SecurityRequirement(name = "spring-security")
public interface UserinfoApi {
  /**
   * 根据id查询用户
   *
   * @return 用户详细信息
   */
  @Operation(operationId = "userinfo", summary = "查询当前用户信息")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserinfoResult.class))}
  )
  @GetMapping
  DataResponse<UserDetails> userinfo();

  /**
   * 修改密码
   *
   * @param request 修改密码请求实体
   * @return 操作结果
   */
  @Operation(operationId = "changePassword", summary = "修改密码")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @PutMapping("change-password")
  Response update(@RequestBody ChangePasswordRequest request);

  @Schema(description = "用户信息结果")
  class UserinfoResult extends DataResponse<UserDetailsImpl> {

  }
}
