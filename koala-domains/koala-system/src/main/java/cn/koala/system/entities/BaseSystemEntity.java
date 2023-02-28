package cn.koala.system.entities;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;
import cn.koala.mybatis.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 基础系统数据实体抽象类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseSystemEntity implements IdModel<Long>, SortModel, StateModel, AuditModel<Long> {
  @Schema(description = "主键")
  protected Long id;
  @Schema(description = "排序索引")
  protected Long sortIndex;
  @Schema(description = "是否启用")
  protected YesNo isEnable;
  @Schema(description = "是否系统")
  protected YesNo isSystem;
  @Schema(description = "是否删除")
  protected YesNo isDelete;
  @Schema(description = "创建人id")
  protected Long createUserId;
  @Schema(description = "创建时间")
  protected Date createTime;
  @Schema(description = "最后更新人id")
  protected Long lastUpdateUserId;
  @Schema(description = "最后更新时间")
  protected Date lastUpdateTime;
  @Schema(description = "最后删除人id")
  protected Long deleteUserId;
  @Schema(description = "最后删除时间")
  protected Date deleteTime;
}
