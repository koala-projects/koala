package cn.koala.task;

import cn.koala.persist.domain.EnumAdvice;
import cn.koala.persist.domain.Persistable;
import lombok.Getter;

import java.util.Date;

/**
 * 任务日志接口
 *
 * @author Houtaroy
 */
public interface TaskLog extends Persistable<Long> {

  Long getTaskId();

  Status getTaskStatus();

  String getTaskError();

  Date getStartTime();

  Date getEndTime();

  @Getter
  enum Status implements EnumAdvice {

    RUNNING("运行中", 0),
    SUCCESS("成功", 1),
    FAIL("失败", -1);

    private final String name;
    private final int value;

    Status(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }
}
