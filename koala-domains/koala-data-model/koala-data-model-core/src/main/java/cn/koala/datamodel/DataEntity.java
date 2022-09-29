package cn.koala.datamodel;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 持久化数据实体
 *
 * @author Houtaroy
 */
@lombok.Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DataEntity implements Data {
  public static final String DEFAULT_ID_KEY = "_koala_data_id";
  protected String id;
  protected List<DataElementEntity> elements;
  protected MetadataEntity metadata;

  /**
   * 根据持久化元数据和内容生成持久化数据对象
   *
   * @param metaData 持久化元数据
   * @param contents 全部数据内容
   * @return 持久化数据对象
   */
  public static DataEntity fromMetaDataAndContents(MetadataEntity metaData, Map<String, Object> contents) {
    DataEntity result = new DataEntity();
    result.setId(contents.getOrDefault(DEFAULT_ID_KEY, UUID.randomUUID()).toString());
    result.setMetadata(metaData);
    result.setElements(
      metaData.getProperties().stream()
        .map(property -> DataElementEntity.fromProperty(property, contents.get(property.getCode()))).toList()
    );
    return result;
  }
}
