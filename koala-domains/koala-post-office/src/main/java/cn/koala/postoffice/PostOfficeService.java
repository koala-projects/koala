package cn.koala.postoffice;

import java.util.List;
import java.util.Map;

/**
 * 驿站服务接口
 *
 * @author Houtaroy
 */
public interface PostOfficeService {

  List<PostOffice> list();

  PostResult post(String officeName, Map<String, Object> original);
}
