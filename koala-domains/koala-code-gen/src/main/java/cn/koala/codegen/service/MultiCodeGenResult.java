package cn.koala.codegen.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiCodeGenResult {

  private String tableName;

  private List<CodeGenResult> codeGenResults;
}
