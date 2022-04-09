package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.domain.models.Api;
import cn.houtaroy.koala.domain.services.ApiService;
import cn.houtaroy.koala.infrastructure.repositories.ApiRepository;
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
