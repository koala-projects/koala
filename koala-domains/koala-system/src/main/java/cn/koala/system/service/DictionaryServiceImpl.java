package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.Dictionary;
import cn.koala.system.repository.DictionaryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 字典服务实现类
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class DictionaryServiceImpl extends AbstractSmartService<Long, Dictionary, Long> implements DictionaryService {

  private final DictionaryRepository repository;
}
