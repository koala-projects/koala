package cn.koala.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 修改密码
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ChangePasswordRequest {
  private String passwordOld;
  private String passwordNew;
}
