package cn.koala.task;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Sortable;
import cn.koala.persist.domain.Stateful;

/**
 * 任务定义接口
 *
 * @author Houtaroy
 */
public interface Task extends Persistable<Long>, Sortable, Stateful, Auditable<Long> {

  String getName();

  String getRemark();

  String getTaskConfig();

  String getTriggerConfig();
}
