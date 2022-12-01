package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataElement;
import cn.koala.datamodel.DataElementService;
import cn.koala.mybatis.AbstractKoalaService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 数据元服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class DataElementServiceImpl extends AbstractKoalaService<DataElement> implements DataElementService {
  protected final DataElementRepository repository;
}
