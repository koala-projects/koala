package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.strategy.NotifyStrategy;
import cn.koala.admin.server.strategy.NotifyStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 内存通知策略服务类
 *
 * @author Houtaroy
 */
@Slf4j
public class InMemoryNotifyStrategyService implements NotifyStrategyService {

  private final List<NotifyStrategy> originals;
  private final Map<String, NotifyStrategy> strategies;

  public InMemoryNotifyStrategyService(List<NotifyStrategy> strategies) {
    if (CollectionUtils.isEmpty(strategies)) {
      this.originals = new ArrayList<>();
      this.strategies = new ConcurrentReferenceHashMap<>();
    } else {
      this.originals = new ArrayList<>(strategies);
      this.strategies = new ConcurrentReferenceHashMap<>(strategies.size());
      strategies.forEach(this::create);
    }
  }


  @Override
  public List<NotifyStrategy> list() {
    return Collections.unmodifiableList(this.originals);
  }

  @Override
  public NotifyStrategy load(String name) {
    return this.strategies.get(name);
  }

  @Override
  public void create(NotifyStrategy strategy) {
    if (this.strategies.containsKey(strategy.getName())) {
      LOGGER.warn("[koala-admin-server]: 通知策略[{}]重复, 已自动覆盖", strategy.getName());
    }
    this.strategies.put(strategy.getName(), strategy);
  }

  @Override
  public void delete(NotifyStrategy strategy) {
    this.strategies.remove(strategy.getName());
  }
}
