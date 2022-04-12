package cn.houtaroy.koala.component.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Table {
  private String name;
  private String comment;
  private List<Column> columns;
}
