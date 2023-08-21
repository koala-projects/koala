package cn.koala.system.support;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限登记器配置
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class PermissionRegistrarConfig {

  private boolean enabled = false;

  private boolean overwritten = false;
}
