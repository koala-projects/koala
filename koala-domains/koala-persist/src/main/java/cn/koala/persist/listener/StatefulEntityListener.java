package cn.koala.persist.listener;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

/**
 * 状态模型监听器
 *
 * @author Houtaroy
 */
@Deprecated
@Order(1000)
public class StatefulEntityListener extends AbstractEntityListener<Stateful> {

  @Override
  public void preAdd(Stateful entity) {
    entity.setIsEnabled(YesNo.YES);
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @Override
  public void preUpdate(Stateful entity, Stateful persist) {
    Assert.notNull(persist, "数据不存在");
    Assert.isTrue(isNonSystemic(persist), "系统数据不允许修改");
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @Override
  public void preDelete(Stateful entity, Stateful persist) {
    Assert.notNull(persist, "数据不存在");
    Assert.isTrue(isNonSystemic(persist), "系统数据不允许删除");
    entity.setIsDeleted(YesNo.YES);
  }

  protected boolean isNonSystemic(Stateful persist) {
    return persist.getIsSystemic() == YesNo.NO;
  }
}
