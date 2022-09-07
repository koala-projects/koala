package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.Data;
import cn.koala.datamodel.DataService;
import cn.koala.datamodel.MetaData;
import cn.koala.datamodel.PersistentData;
import cn.koala.datamodel.PersistentMetaData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class MyBatisDataService implements DataService {
  protected final DataRepository repository;
  protected final MetaDataRepository metaDataRepository;

  @Override
  public Optional<Map<String, Object>> load(String id) {
    return getRepository().findById(id).map(Data::toMap);
  }

  @Override
  public void add(MetaData metaData, Map<String, Object> contents) {
    if (metaData instanceof PersistentMetaData temp) {
      getRepository().add(new PersistentData(temp, contents));
      return;
    }
    throw new IllegalStateException("非持久化类型的元数据[%s]无法进行持久化存储".formatted(metaData.getClass().getName()));
  }

  @Override
  public void update(String id, Map<String, Object> contents) {
    getRepository().findById(id).ifPresent(persistence -> {
      persistence.getElements().forEach(element -> element.setContent(contents));
      getRepository().update(persistence);
    });
  }

  @Override
  public void delete(String id) {
    getRepository().delete(PersistentData.builder().id(id).build());
  }
}
