package cn.koala.mybatis;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 实体抽象类
 * <p>
 * 实现了部分常用数据模型接口, 可用于简化业务实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractEntity<T> implements Persistable<T>, Sortable, Stateful, Auditable<Long> {

  @Schema(description = "主键")
  protected T id;

  @Schema(description = "排序索引")
  protected Long sortIndex;

  @Schema(description = "是否启用")
  protected YesNo isEnabled;

  @Schema(description = "是否系统")
  protected YesNo isSystemic;

  @Schema(description = "是否删除")
  protected YesNo isDeleted;

  @Schema(description = "创建者")
  protected Long createdBy;

  @Schema(description = "创建时间")
  protected Date createdTime;

  @Schema(description = "最后修改者")
  protected Long lastModifiedBy;

  @Schema(description = "最后更新时间")
  protected Date lastModifiedTime;

  @Schema(description = "最后删除者")
  protected Long deletedBy;
  
  @Schema(description = "最后删除时间")
  protected Date deletedTime;
}
