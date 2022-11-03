package cn.koala.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "用户详情数据实体")
public class UserDetailsEntity extends UserEntity implements UserDetails {
  @JsonIgnore
  @Schema(hidden = true)
  private List<String> permissionCodes;

  @Schema(description = "用户权限列表")
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