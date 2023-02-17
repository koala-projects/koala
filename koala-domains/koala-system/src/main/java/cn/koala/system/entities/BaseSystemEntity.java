package cn.koala.system.entities;

import cn.koala.mybatis.AuditModel;
import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.SortModel;
import cn.koala.mybatis.StateModel;
import cn.koala.mybatis.YesNo;
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
  protected Long id;
  protected Long sortIndex;
  protected YesNo isEnable;
  protected YesNo isSystem;
  protected YesNo isDelete;
  protected Long createUserId;
  protected Date createTime;
  protected Long lastUpdateUserId;
  protected Date lastUpdateTime;
  protected Long deleteUserId;
  protected Date deleteTime;
}
