package cn.koala.authorization.jackson;

import cn.koala.persist.domain.YesNo;
import cn.koala.security.userdetails.KoalaUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Set;

/**
 * UserDetails实现反序列化器
 *
 * @author Houtaroy
 */
public class KoalaUserDeserializer extends JsonDeserializer<KoalaUser> {
  private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<>() {
  };

  @Override
  public KoalaUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    throws IOException {
    ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
    JsonNode jsonNode = mapper.readTree(jsonParser);
    Set<SimpleGrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
      SIMPLE_GRANTED_AUTHORITY_SET);
    Long id = readJsonNode(jsonNode, "id").asLong();
    String username = readJsonNode(jsonNode, "username").asText();
    String password = readJsonNode(jsonNode, "password").asText("");
    String nickname = readJsonNode(jsonNode, "nickname").asText("");
    YesNo isEnable = YesNo.valueOf(readJsonNode(jsonNode, "isEnable").asText("NO"));
    return KoalaUser.builder()
      .id(id)
      .username(username)
      .password(password)
      .nickname(nickname)
      .isEnabled(isEnable)
      .authorities(authorities)
      .build();
  }

  private JsonNode readJsonNode(JsonNode jsonNode, String field) {
    return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
  }
}
