package cn.koala.system.service;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.model.Duty;
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
public class DutyServiceImpl extends AbstractMyBatisService<Duty, Long> implements DutyService {

  protected final DutyRepository repository;
}
