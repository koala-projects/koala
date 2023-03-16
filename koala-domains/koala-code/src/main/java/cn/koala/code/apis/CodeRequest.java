package cn.koala.code.apis;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class CodeRequest {
  private Long databaseId;
  private List<String> tables;
  private Long templateGroupId;
}
