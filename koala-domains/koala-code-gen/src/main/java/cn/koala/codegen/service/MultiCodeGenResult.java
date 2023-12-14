package cn.koala.codegen.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 多个代码生成结果
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiCodeGenResult {

  private String tableName;

  private List<CodeGenResult> codeGenResults;
}
