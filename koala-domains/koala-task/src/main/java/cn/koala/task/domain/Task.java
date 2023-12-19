package cn.koala.task.domain;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Sortable;
import cn.koala.data.domain.Systemic;

/**
 * 任务定义接口
 *
 * @author Houtaroy
 */
public interface Task extends Auditable<Long, Long>, Sortable, Enableable, Systemic {

  String getName();

  String getDescription();

  String getTaskConfig();

  String getTriggerConfig();
}
