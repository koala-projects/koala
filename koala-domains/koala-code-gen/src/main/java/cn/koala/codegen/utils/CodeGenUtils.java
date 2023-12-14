package cn.koala.codegen.utils;

import cn.koala.database.domain.DatabaseTableColumn;

/**
 * @author Houtaroy
 */
public abstract class CodeGenUtils {

  public static boolean isId(DatabaseTableColumn column) {
    return CodeGenNames.COLUMN_ID.equals(column.getName());
  }
}
