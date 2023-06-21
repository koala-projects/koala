package cn.koala.datasource.support.mapper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Hikari连接池配置映射
 *
 * @author Houtaroy
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HikariMapper {

  void copy(HikariConfig config, @MappingTarget HikariDataSource dataSource);
}
