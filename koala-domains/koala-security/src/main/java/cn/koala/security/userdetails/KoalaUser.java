package cn.koala.security.userdetails;

import cn.koala.persist.domain.YesNo;
import cn.koala.security.userdetails.support.UserDetailsHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * UserDetails实现类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class KoalaUser implements UserDetails {

  private Long id;

  private String username;

  private String password;

  private String nickname;

  private YesNo isEnabled;

  private List<String> permissionCodes;

  private Collection<SimpleGrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (CollectionUtils.isEmpty(authorities)) {
      this.authorities = UserDetailsHelper.wrap(this.permissionCodes);
    }
    return authorities;
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
    return isEnabled == YesNo.YES;
  }
}
