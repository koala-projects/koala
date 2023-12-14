package cn.koala.codegen.utils;

import java.util.Set;

/**
 * @author Houtaroy
 */
public interface CodeGenNames {

  String COLUMN_ID = "id";

  String COLUMN_SORTABLE = "sort_index";

  String COLUMN_ENABLEABLE = "enabled";

  String COLUMN_SYSTEMIC = "systemic";

  Set<String> COLUMN_AUDITABLE = Set.of(
    "deleted",
    "created_by",
    "created_date",
    "last_modified_by",
    "last_modified_date",
    "deleted_by",
    "deleted_date"
  );

  Set<String> COLUMN_AUDITABLE_NEGLECT = Set.of(
    "deleted",
    "deleted_by",
    "deleted_date"
  );
}
