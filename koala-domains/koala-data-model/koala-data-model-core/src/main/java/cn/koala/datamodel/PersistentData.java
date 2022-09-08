package cn.koala.datamodel;

import cn.koala.persistence.Idable;

/**
 * 持久化数据
 *
 * @author Houtaroy
 */
public interface PersistentData extends Data, Idable<String> {
}
