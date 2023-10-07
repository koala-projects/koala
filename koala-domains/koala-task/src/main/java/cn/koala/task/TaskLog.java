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

  Execution getExecution();

  void setExecution(Execution execution);

  Status getTaskStatus();

  void setTaskStatus(Status taskStatus);

  String getTaskError();

  void setTaskError(String taskError);

  Date getStartTime();

  Date getEndTime();

  void setEndTime(Date endTime);

  @Getter
  enum Status implements EnumAdvice {

    SUCCESS("成功", 1),
    FAIL("失败", -1);

    private final String name;
    private final int value;

    Status(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }

  @Getter
  enum Execution implements EnumAdvice {

    AUTO("自动", 1),
    MANUAL("手动", 2);

    private final String name;
    private final int value;

    Execution(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }
}
