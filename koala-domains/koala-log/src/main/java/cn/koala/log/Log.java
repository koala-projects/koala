package cn.koala.log;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.YesNo;

import java.util.Date;

/**
 * 日志数据实体接口
 *
 * @author Houtaroy
 */
public interface Log extends Persistable<Long> {
  String getModule();

  String getContent();

  Long getUserId();

  String getUserIp();

  YesNo getIsSucceeded();

  String getRequest();

  String getResponse();

  String getError();

  Long getCost();

  Date getLogTime();
}
