package cn.koala.system.api.request;

import cn.koala.system.domain.UserEntity;
import cn.koala.system.model.UserCreateListener;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@EntityListeners(UserCreateListener.class)
public class UserCreateRequest extends UserEntity {

  @Schema(description = "明文密码")
  @NotBlank(message = "用户密码不能为空")
  @Size(min = 6, max = 20, message = "用户密码长度必须在6-20之间")
  private String plainPassword;
}
