package cn.koala.system;

import cn.koala.system.entities.UserEntity;
import cn.koala.system.models.User;
import cn.koala.system.web.DataResponse;
import cn.koala.system.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 */
@RequestMapping("/users")
@RestController
@Tag(name = "user", description = "用户接口")
public interface UserApi {
  /**
   * 根据条件分页查询用户
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 用户列表
   */
  @Operation(summary = "根据条件分页查询用户", tags = {"user"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "昵称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "email", description = "邮箱", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "phone", description = "手机号", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<User>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询用户
   *
   * @param id 用户id
   * @return 用户
   */
  @Operation(summary = "根据id查询用户", tags = {"user"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<User> loadById(@PathVariable("id") String id);

  /**
   * 创建用户
   *
   * @param user 用户
   * @return 用户
   */
  @Operation(summary = "创建用户", tags = {"user"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResult.class))}
  )
  @PostMapping
  DataResponse<User> create(@RequestBody UserEntity user);

  /**
   * 更新用户
   *
   * @param id   用户id
   * @param user 用户
   * @return 操作结果
   */
  @Operation(summary = "更新用户", tags = {"user"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody UserEntity user);

  /**
   * 删除用户
   *
   * @param id 用户id
   * @return 操作结果
   */
  @Operation(summary = "删除用户", tags = {"user"})
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "用户id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  class UserPageResult extends DataResponse<Page<User>> {

  }

  class UserResult extends DataResponse<User> {

  }
}
