package cn.koala.log;

import cn.koala.data.domain.YesNo;
import org.springframework.data.domain.Persistable;

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

  YesNo getSuccessful();

  String getRequest();

  String getResponse();

  String getError();

  Long getCost();

  Date getLogTime();
}
