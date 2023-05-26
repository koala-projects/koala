package cn.koala.admin.server.strategy;

import java.util.List;

/**
 * 通知策略服务接口
 *
 * @author Houtaroy
 */
public interface NotifyStrategyService {

  List<NotifyStrategy> list();

  NotifyStrategy load(String name);

  void create(NotifyStrategy strategy);

  void delete(NotifyStrategy strategy);
}
