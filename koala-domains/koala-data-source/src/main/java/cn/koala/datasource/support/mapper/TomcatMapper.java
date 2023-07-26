package cn.koala.datasource.support.mapper;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.sql.SQLException;

/**
 * Tomcat连接池配置映射
 *
 * @author Houtaroy
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TomcatMapper {

  TomcatMapper INSTANCE = Mappers.getMapper(TomcatMapper.class);

  void copy(PoolConfiguration source, @MappingTarget PoolConfiguration target) throws SQLException;
}
