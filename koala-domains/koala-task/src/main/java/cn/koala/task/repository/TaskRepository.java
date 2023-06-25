package cn.koala.task.repository;

import cn.koala.persist.CrudRepository;
import cn.koala.task.support.TaskEntity;

/**
 * 任务仓库接口
 *
 * @author Koala Code Generator
 */
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}
