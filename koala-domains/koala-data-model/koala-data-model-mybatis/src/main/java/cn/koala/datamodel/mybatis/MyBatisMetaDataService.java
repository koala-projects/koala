package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.MetaData;
import cn.koala.datamodel.MetaDataService;
import cn.koala.mybatis.AbstractCrudService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class MyBatisMetaDataService extends AbstractCrudService<String, MetaData> implements MetaDataService {
  protected final MetaDataRepository repository;
}
