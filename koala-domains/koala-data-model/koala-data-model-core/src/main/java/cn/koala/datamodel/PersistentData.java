package cn.koala.datamodel;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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
public class PersistentData implements Data {
  public static final String DEFAULT_ID_KEY = "_koala_data_id";
  protected String id;
  protected List<PersistentDataElement> elements;
  protected PersistentMetadata metadata;

  /**
   * 根据持久化元数据和内容生成持久化数据对象
   *
   * @param metaData 持久化元数据
   * @param contents 全部数据内容
   * @return 持久化数据对象
   */
  public static PersistentData fromMetaDataAndContents(PersistentMetadata metaData, Map<String, Object> contents) {
    PersistentData result = new PersistentData();
    result.setId(contents.getOrDefault(DEFAULT_ID_KEY, UUID.randomUUID()).toString());
    result.setMetadata(metaData);
    List<PersistentDataElement> elements = new ArrayList<>(metaData.getProperties().size());
    metaData.getProperties().forEach(property -> {
      PersistentDataElement element = PersistentDataElement.fromProperty(property);
      element.setData(result);
      element.fromContents(contents);
      elements.add(element);
    });
    result.setElements(elements);
    return result;
  }
}
