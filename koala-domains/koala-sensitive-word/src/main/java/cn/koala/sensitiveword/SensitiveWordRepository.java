package cn.koala.sensitiveword;

import java.util.List;

/**
 * 敏感词存储库类
 *
 * @author Houtaroy
 */
public interface SensitiveWordRepository {
  /**
   * 查询全部敏感词
   *
   * @return 敏感词列表
   */
  List<String> findAll();
}
