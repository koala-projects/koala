package cn.koala.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 创建数据请求类
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateDataRequest {
  private PersistentMetadata metadata;
  private Map<String, Object> contents;
}
