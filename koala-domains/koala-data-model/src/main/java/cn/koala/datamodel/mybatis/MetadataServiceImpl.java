package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataRecordService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.PropertyEntity;
import cn.koala.datamodel.PropertyService;
import cn.koala.mybatis.AbstractKoalaService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;

/**
 * 元数据服务, MyBatis实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MetadataServiceImpl extends AbstractKoalaService<Metadata> implements MetadataService {
  protected final MetadataRepository repository;
  protected final PropertyService propertyService;
  protected final DataRecordService dataRecordService;

  @Override
  public void add(Metadata entity) {
    super.add(entity);
    addProperties(entity);
  }

  @Override
  public void update(Metadata entity) {
    Optional<Metadata> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    Assert.isTrue(isNoRecord(persist.get()), "元数据存在数据记录, 不允许修改");
    repository.update(entity);
    persist.get().getProperties().forEach(propertyService::delete);
    addProperties(entity);
  }

  @Override
  public void delete(Metadata entity) {
    Optional<Metadata> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    Assert.isTrue(isNoRecord(persist.get()), "元数据存在数据记录, 不允许删除");
    repository.delete(entity);
    persist.get().getProperties().forEach(propertyService::delete);
  }

  /**
   * 增加属性列表
   *
   * @param entity 元数据实体
   */
  protected void addProperties(Metadata entity) {
    entity.getProperties().forEach(property -> {
      if (property instanceof PropertyEntity propertyEntity) {
        propertyEntity.setMetadata(MetadataEntity.builder().id(entity.getId()).build());
      }
      propertyService.add(property);
    });
  }

  protected boolean isNoRecord(Metadata metadata) {
    return dataRecordService.list(Map.of("metadataId", metadata.getId())).isEmpty();
  }
}
