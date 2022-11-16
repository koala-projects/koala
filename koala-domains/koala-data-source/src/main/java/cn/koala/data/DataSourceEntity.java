package cn.koala.data;

import cn.koala.enhancement.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Houtaroy
 */
@Schema(description = "部门代码")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class DataSourceEntity implements DataSource {
  @Schema(description = "ID")
  protected String id;
  @Schema(description = "数据源名称")
  protected String name;
  @Schema(description = "数据源连接")
  protected String url;
  @Schema(description = "数据源用户名")
  protected String username;
  @Schema(description = "数据源密码")
  protected String password;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
}
