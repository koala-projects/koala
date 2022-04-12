package cn.houtaroy.koala.component.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Driver;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Database {
  protected String name;
  protected Driver driver;
}
