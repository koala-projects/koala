package cn.koala.log;

import cn.koala.mybatis.IdModel;

import java.util.Date;

/**
 * 日志数据实体接口
 *
 * @author Houtaroy
 */
public interface Log extends IdModel<Long> {
  String getModule();

  String getContent();

  Date getLogTime();

  Long getUserId();

  String getUserIp();

  String getRequest();

  String getResponse();

  Long getCost();
}
