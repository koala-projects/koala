package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.Dictionary;
import cn.koala.system.DictionaryService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 字典服务MyBatis实现
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class MyBatisDictionaryService extends AbstractUUIDCrudService<Dictionary> implements DictionaryService {
  protected final DictionaryRepository repository;
}
