package cn.koala.datamodel;

import cn.koala.persistence.Idable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据元实体
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class DataElementEntity implements DataElement, Idable<String> {
  protected String id;
  protected String code;
  protected String content;
  protected PropertyEntity property;
  protected DataRecordEntity dataRecord;
}
