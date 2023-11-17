package cn.koala.datasource.support.mapper;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * Druid连接池配置映射
 *
 * @author Houtaroy
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DruidMapper {

  DruidMapper INSTANCE = Mappers.getMapper(DruidMapper.class);

  void copy(DruidDataSourceWrapper config, @MappingTarget DruidDataSourceWrapper dataSource) throws Exception;
}
