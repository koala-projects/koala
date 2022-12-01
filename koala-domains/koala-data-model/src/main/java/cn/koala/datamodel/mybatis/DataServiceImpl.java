package cn.koala.datamodel.mybatis;

import cn.koala.datamodel.DataElement;
import cn.koala.datamodel.DataElementEntity;
import cn.koala.datamodel.DataElementService;
import cn.koala.datamodel.DataRecord;
import cn.koala.datamodel.DataRecordEntity;
import cn.koala.datamodel.DataRecordService;
import cn.koala.datamodel.DataService;
import cn.koala.datamodel.Metadata;
import cn.koala.datamodel.MetadataEntity;
import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.PropertyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

  protected final DataRecordService dataRecordService;
  protected final DataElementService dataElementService;
  protected final MetadataService metadataService;

  @Override
  public Page<Map<String, Object>> list(Map<String, Object> parameters, Pageable pageable) {
    List<Map<String, Object>> result = dataRecordService.list(parameters, pageable)
      .getContent()
      .stream()
      .map(this::record2Data)
      .toList();
    return new PageImpl<>(result, pageable, result.size());
  }

  @Override
  public List<Map<String, Object>> list(Map<String, Object> parameters) {
    return dataRecordService.list(parameters).stream().map(this::record2Data).toList();
  }

  @Override
  public Optional<Map<String, Object>> load(String id) {
    return dataRecordService.load(id).map(this::record2Data);
  }

  @Override
  public void add(Metadata metadata, Map<String, Object> data) {
    DataRecordEntity record = DataRecordEntity.builder()
      .id(data.getOrDefault("id", UUID.randomUUID()).toString())
      .metadata(MetadataEntity.builder().id(metadata.getId()).build())
      .build();
    dataRecordService.add(record);
    addElements(record, data);
  }

  @Override
  public void update(String id, Map<String, Object> data) {
    Optional<DataRecord> persist = dataRecordService.load(id);
    Assert.isTrue(persist.isPresent(), "数据不存在");
    dataElementService.list(Map.of("dataRecordId", id)).forEach(dataElementService::delete);
    addElements(persist.get(), data);
  }

  @Override
  public void delete(String id) {
    dataRecordService.delete(DataRecordEntity.builder().id(id).build());
    dataElementService.list(Map.of("dataRecordId", id)).forEach(dataElementService::delete);
  }

  /**
   * 数据记录转换为数据
   *
   * @param record 数据记录
   * @return 数据
   */
  protected Map<String, Object> record2Data(DataRecord record) {
    List<DataElement> elements = dataElementService.list(Map.of("dataRecordId", record.getId()));
    Map<String, Object> result = new HashMap<>(elements.size());
    elements.forEach(element -> result.put(element.getCode(), element.parseContent()));
    result.put("id", record.getId());
    return result;
  }

  /**
   * 增加数据元列表
   *
   * @param record 数据记录
   * @param data   数据内容
   */
  protected void addElements(DataRecord record, Map<String, Object> data) {
    Optional<Metadata> metadata = metadataService.load(record.getMetadata().getId());
    Assert.isTrue(metadata.isPresent(), "数据异常, 请联系管理员");
    metadata.get().getProperties().forEach(property -> dataElementService.add(
      DataElementEntity.builder()
        .id(UUID.randomUUID().toString())
        .code(property.getCode())
        .content(data.getOrDefault(property.getCode(), "").toString())
        .property(PropertyEntity.builder().id(property.getId()).build())
        .dataRecord(DataRecordEntity.builder().id(record.getId()).build())
        .build()
    ));
  }
}
