package cn.koala.postoffice;

import java.util.Map;

/**
 * 驿站
 *
 * @author Houtaroy
 */
public interface PostOffice {

  String getName();

  void post(Map<String, Object> original) throws Exception;
}
