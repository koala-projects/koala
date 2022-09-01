package cn.koala.datamodel;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 持久化数据
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
  protected PersistentMetaData metaData;

  /**
   * 根据元数据和全部数据内容创建数据对象
   *
   * @param metaData 元数据
   * @param contents 全部数据内容
   */
  public PersistentData(PersistentMetaData metaData, Map<String, Object> contents) {
    Validate.notNull(metaData, "元数据不能为空");
    this.metaData = metaData;
    initId(contents);
    initElements(contents);
  }

  /**
   * 根据全部数据内容初始化id, 读取默认ID键值, 如数据不存在则自动生成为UUID
   *
   * @param contents 全部数据内容
   */
  protected void initId(Map<String, Object> contents) {
    id = contents.getOrDefault(DEFAULT_ID_KEY, UUID.randomUUID()).toString();
  }

  /**
   * 根据全部数据内容初始化数据元列表
   *
   * @param contents 全部数据内容
   */
  protected void initElements(Map<String, Object> contents) {
    elements = new ArrayList<>(contents.size());
    metaData.getProperties().forEach(property -> {
      PersistentDataElement element = PersistentDataElement.builder()
        .code(property.getCode())
        .property(property)
        .data(this)
        .metaData(metaData)
        .build();
      element.setContent(contents);
      elements.add(element);
    });
  }
}
