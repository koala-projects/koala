package cn.koala.system;

import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Houtaroy
 */
@RequestMapping("/api/personal")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "个人服务")
public interface PersonalApi {

  /**
   * 修改密码
   *
   * @param principal 用户安全主体信息
   * @param request   修改密码请求参数
   * @return 操作结果
   */
  @PreAuthorize("hasAuthority('personal')")
  @Operation(summary = "修改密码")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @PutMapping("change-password")
  Response changePassword(@Parameter(hidden = true) Principal principal,
                          @RequestBody ChangePasswordRequest request);
}
