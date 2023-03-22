package cn.koala.persist.listener;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import org.springframework.util.Assert;

/**
 * 状态模型监听器
 *
 * @author Houtaroy
 */
public class StatefulEntityListener extends BaseEntityListener {
  public StatefulEntityListener() {
    super(Stateful.class);
  }

  @Override
  public void beforeAdd(Object entity) {
    if (entity instanceof Stateful state) {
      state.setIsEnabled(YesNo.YES);
      state.setIsSystemic(YesNo.NO);
      state.setIsDeleted(YesNo.NO);
    }
  }

  @Override
  public void afterAdd(Object entity) {

  }

  @Override
  public void beforeUpdate(Object entity, Object persist) {
    Assert.notNull(persist, "数据不存在");
    Assert.isTrue(isNonSystemic(persist), "系统数据不允许修改");
    if (entity instanceof Stateful state) {
      state.setIsSystemic(YesNo.NO);
      state.setIsDeleted(YesNo.NO);
    }
  }

  @Override
  public void afterUpdate(Object entity) {

  }

  @Override
  public void beforeDelete(Object entity, Object persist) {
    Assert.notNull(persist, "数据不存在");
    Assert.isTrue(isNonSystemic(persist), "系统数据不允许删除");
    if (entity instanceof Stateful state) {
      state.setIsDeleted(YesNo.YES);
    }
  }

  @Override
  public void afterDelete(Object entity) {

  }

  protected boolean isNonSystemic(Object persist) {
    if (persist instanceof Stateful stateful) {
      return stateful.getIsSystemic() == YesNo.NO;
    }
    return true;
  }
}
