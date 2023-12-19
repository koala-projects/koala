package cn.koala.task.repository;

import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.task.domain.Task;

/**
 * 任务仓库接口
 *
 * @author Koala Code Generator
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
