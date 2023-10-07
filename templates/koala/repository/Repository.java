package #(package).repository;

import #(package).entity.#(name)Entity;

import cn.koala.persist.repository.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Generator
 */
public interface #(name)Repository extends CrudRepository<#(name)Entity, #(entity.properties.id.type)> {
}
