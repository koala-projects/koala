package cn.koala.data.mybatis;

import cn.koala.data.DataSource;
import cn.koala.data.DataSourceService;
import cn.koala.mybatis.AbstractSmartService;
import cn.koala.mybatis.IdGenerator;
import cn.koala.mybatis.UUIDGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSourceServiceImpl extends AbstractSmartService<String, DataSource> implements DataSourceService {
  protected final IdGenerator<DataSource, String> idGenerator = new UUIDGenerator<>();
  protected final DataSourceRepository repository;
}
