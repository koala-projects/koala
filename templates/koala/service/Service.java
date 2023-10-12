package #(package).service;

import #(package).entity.#(name.pascal.singular)Entity;
import #(package).repository.#(name.pascal.singular)Repository;

import cn.koala.mybatis.AbstractMyBatisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * #(description)服务类
 *
 * @author Koala Code Gen
 */
@Component
@Getter
@RequiredArgsConstructor
public class #(name.pascal.singular)Service extends AbstractMyBatisService<#(name.pascal.singular)Entity, #(id.type.java)> {
  
  protected final #(name.pascal.singular)Repository repository;
}
