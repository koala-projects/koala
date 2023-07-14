package cn.koala.postoffice.support;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.PostOfficeService;
import cn.koala.postoffice.PostResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基于内存的驿站服务实现类
 *
 * @author Houtaroy
 */
@Slf4j
public class InMemoryPostOfficeService implements PostOfficeService {

  private final List<PostOffice> offices;
  private final Map<String, PostOffice> repository;

  public InMemoryPostOfficeService(List<PostOffice> offices) {
    this.offices = offices;
    this.repository = offices.stream().collect(Collectors.toMap(PostOffice::getName, office -> office));
  }

  @Override
  public List<PostOffice> list() {
    return this.offices;
  }

  @Override
  public PostResult post(String officeName, Map<String, Object> original) {
    PostOffice office = this.repository.get(officeName);
    if (office == null) {
      return PostResult.fail(officeName, "驿站不存在");
    }
    try {
      office.post(original);
      return PostResult.success(officeName);
    } catch (Exception e) {
      LOGGER.error("驿站[name={}]投递失败", officeName, e);
      return PostResult.fail(officeName, "投递失败");
    }
  }
}
