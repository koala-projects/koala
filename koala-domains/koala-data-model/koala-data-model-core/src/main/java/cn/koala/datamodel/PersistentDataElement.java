package cn.koala.datamodel;

import cn.koala.persistence.Idable;

/**
 * 持久化数据元
 *
 * @author Houtaroy
 */
public interface PersistentDataElement extends DataElement, Idable<String> {
}
