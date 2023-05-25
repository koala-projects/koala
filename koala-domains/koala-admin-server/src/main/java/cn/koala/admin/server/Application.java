package cn.koala.admin.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 应用
 *
 * @author Houtaroy
 */
@Document
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Application {

  @Id
  private String id;

  @Indexed(unique = true)
  private String name;
}
