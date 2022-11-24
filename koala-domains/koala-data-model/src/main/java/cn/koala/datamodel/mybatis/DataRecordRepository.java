package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataRecord;
import cn.koala.mybatis.PageRepository;

/**
 * 数据存储库
 *
 * @author Houtaroy
 */
public interface DataRecordRepository extends PageRepository<String, DataRecord> {
}
