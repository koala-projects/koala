package cn.koala.system.service;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.model.Dictionary;
import cn.koala.system.repository.DictionaryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 字典服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class DictionaryServiceImpl extends AbstractMyBatisService<Dictionary, Long> implements DictionaryService {

  protected final DictionaryRepository repository;
}
