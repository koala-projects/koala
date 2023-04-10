package cn.koala.system.apis.request;

import cn.koala.system.entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 创建用户请求
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "创建用户请求")
public class CreateUserRequest extends UserEntity {
  @Schema(description = "明文密码")
  private String plainPassword;
}
