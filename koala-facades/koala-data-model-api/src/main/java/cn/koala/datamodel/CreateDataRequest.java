package cn.koala.datamodel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 创建数据请求类
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "创建数据请求参数")
public class CreateDataRequest {
  @Schema(description = "元数据")
  private MetadataEntity metadata;
  @Schema(description = "数据内容, Map对象")
  private Map<String, Object> data;
}
