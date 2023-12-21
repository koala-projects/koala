package cn.koala.mybatis.listener;

/**
 * 全局实体监听器排序
 *
 * @author Houtaroy
 */
public interface GlobalEntityListenerOrders {

  int ENABLEABLE = 9100;

  int SYSTEMIC = 9200;

  int AUDITING = 9300;
}
