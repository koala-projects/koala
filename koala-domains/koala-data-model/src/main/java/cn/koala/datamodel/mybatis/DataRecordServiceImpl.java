package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataRecord;
import cn.koala.datamodel.DataRecordService;
import cn.koala.mybatis.AbstractKoalaService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class DataRecordServiceImpl extends AbstractKoalaService<DataRecord> implements DataRecordService {
  protected final DataRecordRepository repository;

  @Override
  public void update(DataRecord entity) {
    LOGGER.error("数据记录[id={}]更新失败: 数据记录不允许更新", entity.getId());
    throw new IllegalStateException("服务器异常, 请联系管理员");
  }
}
