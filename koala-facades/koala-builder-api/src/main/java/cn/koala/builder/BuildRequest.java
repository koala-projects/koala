package cn.koala.builder;

import cn.koala.jdbc.JdbcTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

/**
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class BuildRequest {
  private JdbcTable table;
  private Map<String, Object> properties;
}
