package cn.koala.mybatis.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;
import cn.koala.data.domain.YesNo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/*
 * 通用实体抽象类
 * <p>
 * 实现了部分常用数据模型接口, 可用于简化业务实体
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class AbstractEntity<U, ID> implements Auditable<U, ID>, Sortable, Enableable, Systemic {

  @Schema(description = "主键")
  private ID id;

  @Schema(description = "排序索引")
  private Long sortIndex;

  @Schema(description = "是否启用")
  private YesNo enabled;

  @Schema(description = "是否系统")
  private YesNo systemic;

  @Schema(description = "是否删除")
  private YesNo deleted;

  @Schema(description = "创建者")
  private U createdBy;

  @Schema(description = "创建时间")
  private Date createdDate;

  @Schema(description = "最后修改者")
  private U lastModifiedBy;

  @Schema(description = "最后更新时间")
  private Date lastModifiedDate;

  @Schema(description = "最后删除者")
  private U deletedBy;

  @Schema(description = "最后删除时间")
  private Date deletedDate;

  @Override
  public boolean isNew() {
    return id == null;
  }
}
