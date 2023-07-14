package cn.koala.postoffice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 投递请求
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
public class PostRequest {

  private String officeName;

  private Map<String, Object> original;
}
