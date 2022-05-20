package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.infrastructure.repositories.ApiRepository;
import cn.koala.system.models.Api;
import cn.koala.system.services.ApiService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class ApiServiceImpl extends BaseCrudService<String, Api> implements ApiService {

  private final ApiRepository repository;
}
