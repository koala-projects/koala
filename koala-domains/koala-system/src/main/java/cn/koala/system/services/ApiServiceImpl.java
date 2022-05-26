package cn.koala.system.services;

import cn.koala.system.models.Api;
import cn.koala.system.repositories.ApiRepository;
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
