package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.Duty;
import cn.koala.system.repository.DutyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 岗位服务类
 *
 * @author Koala Code Gen
 */
@Getter
@RequiredArgsConstructor
public class DefaultDutyService extends AbstractSmartService<Long, Duty, Long> implements DutyService {

  private final DutyRepository repository;
}
