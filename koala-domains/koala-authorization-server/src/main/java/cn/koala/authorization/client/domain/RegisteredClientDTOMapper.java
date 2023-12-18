package cn.koala.authorization.client.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 注册客户端数据传输实体转换Mapper
 *
 * @author Houtaroy
 */
@Mapper
public abstract class RegisteredClientDTOMapper {

  public static final RegisteredClientDTOMapper MAPPER = Mappers.getMapper(RegisteredClientDTOMapper.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Mapping(target = "clientAuthenticationMethods", source = "clientAuthenticationMethods", qualifiedByName = "string2Set")
  @Mapping(target = "authorizationGrantTypes", source = "authorizationGrantTypes", qualifiedByName = "string2Set")
  @Mapping(target = "redirectUris", source = "redirectUris", qualifiedByName = "string2Set")
  @Mapping(target = "scopes", source = "scopes", qualifiedByName = "string2Set")
  @Mapping(target = "clientSettings", source = "clientSettings", qualifiedByName = "string2Map")
  @Mapping(target = "tokenSettings", source = "tokenSettings", qualifiedByName = "string2Map")
  public abstract RegisteredClientDTO map(RegisteredClientEntity source);

  @Named("string2Set")
  public Set<String> string2Set(String source) {
    if (!StringUtils.hasText(source)) {
      return new HashSet<>();
    }
    return Arrays.stream(source.split(",")).collect(Collectors.toSet());
  }

  @Named("string2Map")
  public Map<String, Object> string2Map(String source) throws JsonProcessingException {
    if (!StringUtils.hasText(source)) {
      return new HashMap<>();
    }
    return this.objectMapper.readValue(source, new TypeReference<>() {
    });
  }
}


