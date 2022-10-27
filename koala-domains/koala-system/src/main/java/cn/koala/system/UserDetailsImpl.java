package cn.koala.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * OAuth2用户
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetailsImpl extends UserEntity implements UserDetails {
  private List<String> permissionCodes;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Optional.ofNullable(permissionCodes)
      .map(codes -> codes.stream().map(SimpleGrantedAuthority::new).toList())
      .orElse(new ArrayList<>());
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
    return true;
  }
}
