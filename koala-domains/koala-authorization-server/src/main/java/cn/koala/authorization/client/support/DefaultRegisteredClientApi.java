package cn.koala.authorization.client.support;

import cn.koala.authorization.client.RegisteredClientApi;
import cn.koala.authorization.client.RegisteredClientDTO;
import cn.koala.authorization.client.RegisteredClientService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 注册客户端管理接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DefaultRegisteredClientApi implements RegisteredClientApi {

  private final RegisteredClientService service;

  @Override
  public DataResponse<Page<RegisteredClientDTO>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(this.service.page(parameters, pageable));
  }

  @Override
  public DataResponse<RegisteredClientDTO> load(String id) {
    return DataResponse.ok(this.service.load(id));
  }

  @Override
  public DataResponse<RegisteredClientDTO> create(RegisteredClientDTO dto) {
    this.service.create(dto);
    return DataResponse.ok(dto);
  }

  @Override
  public Response update(String id, RegisteredClientDTO dto) {
    dto.setIdIfAbsent(id);
    this.service.update(dto);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    this.service.delete(RegisteredClientDTO.builder().id(id).build());
    return Response.SUCCESS;
  }
}
