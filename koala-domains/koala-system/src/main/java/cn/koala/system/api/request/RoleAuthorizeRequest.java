package cn.koala.system.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色授权请求参数
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class RoleAuthorizeRequest {
  private List<Long> checkedIds;
  private List<Long> halfCheckedIds;
}
