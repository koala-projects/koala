package cn.houtaroy.koala.services;

import cn.houtaroy.koala.models.Api;
import cn.houtaroy.koala.repositories.ApiRepository;
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
