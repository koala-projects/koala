package cn.koala.builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class BuildDownloadResponse {
  private String url;
}
