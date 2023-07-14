package cn.koala.postoffice.support;

import cn.koala.postoffice.Packer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 基于ObjectMapper的打包器实现类
 *
 * @author Houtaroy
 */
public class ObjectMapperPacker<T> implements Packer<T> {

  private final ObjectMapper objectMapper;
  private final Class<T> parcelClass;

  public ObjectMapperPacker(Class<T> parcelClass) {
    this(new ObjectMapper(), parcelClass);
  }

  public ObjectMapperPacker(ObjectMapper objectMapper, Class<T> parcelClass) {
    this.objectMapper = objectMapper;
    this.parcelClass = parcelClass;
  }


  @Override
  public T pack(Map<String, Object> original) {
    return this.objectMapper.convertValue(original, this.parcelClass);
  }
}
