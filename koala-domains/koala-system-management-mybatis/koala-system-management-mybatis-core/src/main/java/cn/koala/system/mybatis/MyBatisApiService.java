package cn.koala.system.mybatis;

import cn.koala.system.Api;
import cn.koala.system.ApiService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@SuppressWarnings("PMD")
public class MyBatisApiService extends AbstractSystemCrudService<String, Api> implements ApiService {

  protected final ApiRepository repository;
}
