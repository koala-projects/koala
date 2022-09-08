package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.mybatis.AbstractCrudService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class MyBatisMetadataService extends AbstractCrudService<String, PersistentMetadata> implements MetadataService {
  protected final MetadataRepository repository;
}
