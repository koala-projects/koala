package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.Property;
import cn.koala.datamodel.PropertyService;
import cn.koala.mybatis.AbstractKoalaService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * 属性服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PropertyServiceImpl extends AbstractKoalaService<Property> implements PropertyService {
  protected final PropertyRepository repository;
}
