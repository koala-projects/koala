package cn.koala.security;

import cn.koala.mybatis.YesNo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * UserDetails实现类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDetailsImpl implements UserDetails {
  private Long id;
  private String username;
  private String password;
  private String nickname;
  private YesNo isEnable;
  private List<String> permissionCodes;
  private Collection<SimpleGrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (CollectionUtils.isEmpty(authorities)) {
      permission2Authorities();
    }
    return authorities;
  }

  /**
   * 权限代码转换为Authorities
   */
  protected void permission2Authorities() {
    authorities = new HashSet<>(permissionCodes.size());
    permissionCodes.forEach(code -> authorities.add(new SimpleGrantedAuthority(code)));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isEnable == YesNo.YES;
  }
}
