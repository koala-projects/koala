package cn.koala.eucalyptus;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class ConvertProperties {
  private String prefix = "t_";
  private Map<String, String> typeMap = Constant.TYPE_MAP;
}
