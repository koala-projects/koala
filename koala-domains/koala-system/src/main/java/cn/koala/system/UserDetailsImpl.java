package cn.koala.system;

import cn.koala.persistence.Codeable;
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
public class UserDetailsImpl extends UserEntity implements UserDetails {

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> result = new ArrayList<>();
    Optional.ofNullable(getRoles()).ifPresent(roles -> roles.forEach(role ->
      Optional.ofNullable(role.getPermissions()).ifPresent(permissions ->
        result.addAll(permissions.stream().map(Codeable::getCode).map(SimpleGrantedAuthority::new).toList())
      )
    ));
    return result;
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
