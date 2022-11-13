package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataService;
import cn.koala.mybatis.AbstractService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class MyBatisMetadataService extends AbstractService<String, Metadata> implements MetadataService {
  protected final MetadataRepository repository;
}
