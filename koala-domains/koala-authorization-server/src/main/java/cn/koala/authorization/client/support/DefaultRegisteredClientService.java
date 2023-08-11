package cn.koala.authorization.client.support;

import cn.koala.authorization.client.RegisteredClientDTO;
import cn.koala.authorization.client.RegisteredClientEntity;
import cn.koala.authorization.client.RegisteredClientService;
import cn.koala.authorization.client.mapper.RegisteredClientDTOMapper;
import cn.koala.authorization.client.repository.RegisteredClientMyBatisRepository;
import com.github.pagehelper.PageHelper;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 默认注册客户端服务实现类
 *
 * @author Houtaroy
 */
public class DefaultRegisteredClientService implements RegisteredClientService {

  private final RegisteredClientRepository repository;
  private final RegisteredClientMyBatisRepository myBatisRepository;

  public DefaultRegisteredClientService(RegisteredClientRepository repository, RegisteredClientMyBatisRepository myBatisRepository) {
    this.repository = repository;
    this.myBatisRepository = myBatisRepository;
  }

  @Override
  public Page<RegisteredClientDTO> page(Map<String, Object> parameters, Pageable pageable) {
    parameters.put("orders", pageable.getSort().toList());
    com.github.pagehelper.Page<RegisteredClientEntity> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> this.myBatisRepository.list(parameters));
    return new PageImpl<>(
      page.getResult().stream().map(RegisteredClientDTOMapper.INSTANCE::map).toList(),
      pageable,
      page.getTotal()
    );
  }

  @Override
  public List<RegisteredClientDTO> list(Map<String, Object> parameters) {
    return this.myBatisRepository.list(parameters).stream().map(RegisteredClientDTOMapper.INSTANCE::map).toList();
  }

  @Override
  public RegisteredClientDTO load(String id) {
    RegisteredClient persist = this.repository.findById(id);
    Assert.notNull(persist, "注册客户端不存在");
    return RegisteredClientDTO.from(persist);
  }

  @Override
  public void create(@NonNull RegisteredClientDTO dto) {
    this.repository.save(dto.toRegisteredClient());
  }

  @Override
  public void update(@NonNull RegisteredClientDTO dto) {
    this.repository.save(dto.toRegisteredClient());
  }

  @Override
  public void delete(@NonNull RegisteredClientDTO dto) {
    this.myBatisRepository.delete(RegisteredClientEntity.builder().id(dto.getId()).build());
  }
}
