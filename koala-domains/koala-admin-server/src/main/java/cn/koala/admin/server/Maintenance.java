package cn.koala.admin.server;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 运维关系
 *
 * @author Houtaroy
 */
@Document
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Maintenance {

  @Id
  private String id;

  private String applicationId;

  private String maintainerId;

  private String strategy;
}
