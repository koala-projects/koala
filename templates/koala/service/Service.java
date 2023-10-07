package #(package).service;

import #(package).entity.#(name)Entity;
import #(package).repository.#(name)Repository;

import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * #(description)服务类
 *
 * @author Koala Code Generator
 */
@Component
@Getter
@RequiredArgsConstructor
public class #(name)Service extends AbstractMyBatisService<#(name)Entity, #(entity.properties.id.type)> {
  
  protected final #(name)Repository repository;
}
