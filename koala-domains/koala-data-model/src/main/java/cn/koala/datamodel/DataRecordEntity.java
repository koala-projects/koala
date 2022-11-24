package cn.koala.datamodel;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 数据记录实体
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@lombok.Data
public class DataRecordEntity implements DataRecord {
  protected String id;
  protected MetadataEntity metadata;
}
