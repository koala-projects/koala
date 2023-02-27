package cn.koala.log;

import cn.koala.mybatis.IdModel;
import cn.koala.mybatis.YesNo;

import java.util.Date;

/**
 * 日志数据实体接口
 *
 * @author Houtaroy
 */
public interface Log extends IdModel<Long> {
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
