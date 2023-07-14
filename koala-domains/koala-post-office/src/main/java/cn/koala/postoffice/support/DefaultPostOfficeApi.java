package cn.koala.postoffice.support;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.PostOfficeApi;
import cn.koala.postoffice.PostOfficeService;
import cn.koala.postoffice.PostRequest;
import cn.koala.postoffice.PostResult;
import cn.koala.web.DataRequest;
import cn.koala.web.DataResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 默认驿站接口实现类
 *
 * @author Houtaroy
 */
@RestController
public class DefaultPostOfficeApi implements PostOfficeApi {

  private final PostOfficeService service;

  public DefaultPostOfficeApi(PostOfficeService service) {
    this.service = service;
  }

  @Override
  public DataResponse<List<PostOffice>> list() {
    return DataResponse.ok(this.service.list());
  }

  @Override
  public DataResponse<List<PostResult>> post(DataRequest<List<PostRequest>> request) {
    return DataResponse.ok(
      request.getData().stream()
        .map(postRequest -> this.service.post(postRequest.getOfficeName(), postRequest.getOriginal()))
        .toList()
    );
  }
}
