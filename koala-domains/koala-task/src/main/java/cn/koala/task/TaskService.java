package cn.koala.task;

import cn.koala.persist.CrudService;
import cn.koala.task.support.TaskEntity;

/**
 * 任务服务接口
 *
 * @author Houtaroy
 */
public interface TaskService extends CrudService<TaskEntity, Long> {
}
