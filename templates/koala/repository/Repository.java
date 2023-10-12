package #(package).repository;

import #(package).entity.#(name.pascal.singular)Entity;

import cn.koala.persist.repository.CrudRepository;

/**
 * #(description)仓库接口
 *
 * @author Koala Code Gen
 */
public interface #(name.pascal.singular)Repository extends CrudRepository<#(name.pascal.singular)Entity, #(id.type.java)> {
}
