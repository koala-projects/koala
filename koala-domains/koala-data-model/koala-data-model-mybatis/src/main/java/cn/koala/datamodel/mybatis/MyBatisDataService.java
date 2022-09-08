package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataEntity;
import cn.koala.datamodel.DataService;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.PersistentData;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.mybatis.PageEnhancedHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class MyBatisDataService implements DataService {
  protected final DataRepository repository;

  @Override
  public Page<PersistentData> list(Map<String, Object> parameters, Pageable pageable) {
    return PageEnhancedHelper.page(() -> getRepository().findAll(parameters, pageable), pageable);
  }

  @Override
  public List<PersistentData> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }

  @Override
  public Optional<PersistentData> load(String id) {
    return getRepository().findById(id);
  }

  @Override
  public void add(PersistentMetadata metaData, Map<String, Object> contents) {
    if (metaData instanceof MetadataEntity temp) {
      getRepository().add(DataEntity.fromMetaDataAndContents(temp, contents));
      return;
    }
    throw new IllegalStateException("非持久化类型的元数据[%s]无法进行持久化存储".formatted(metaData.getClass().getName()));
  }

  @Override
  public void update(String id, Map<String, Object> contents) {
    getRepository().findById(id).ifPresent(persistence -> {
      persistence.getElements().forEach(element -> element.fromContents(contents));
      getRepository().update(persistence);
    });
  }

  @Override
  public void delete(String id) {
    getRepository().delete(DataEntity.builder().id(id).build());
  }
}
