package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.Data;
import cn.koala.datamodel.DataService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.PersistentData;
import cn.koala.datamodel.PersistentMetadata;
import cn.koala.mybatis.PageEnhancedHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
  public Page<Map<String, Object>> list(Map<String, Object> parameters, Pageable pageable) {
    Page<Data> data = PageEnhancedHelper.page(() -> getRepository().findAll(parameters, pageable), pageable);
    List<Map<String, Object>> result = data.getContent().stream()
      .map(Data::toMap)
      .toList();
    return new PageImpl<>(result, pageable, data.getTotalPages());
  }

  @Override
  public Optional<Map<String, Object>> load(String id) {
    return getRepository().findById(id).map(Data::toMap);
  }

  @Override
  public void add(Metadata metaData, Map<String, Object> contents) {
    if (metaData instanceof PersistentMetadata temp) {
      getRepository().add(PersistentData.fromMetaDataAndContents(temp, contents));
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
    getRepository().delete(PersistentData.builder().id(id).build());
  }
}