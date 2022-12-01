package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.Metadata;
import cn.koala.mybatis.PageRepository;

/**
 * 元数据存储库
 *
 * @author Houtaroy
 */
public interface MetadataRepository extends PageRepository<String, Metadata> {
}
