package cn.koala.admin.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 运维工程师
 *
 * @author Houtaroy
 */
@Document
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Maintainer {

  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  private String nickname;

  private String email;

  private String mobile;
}
