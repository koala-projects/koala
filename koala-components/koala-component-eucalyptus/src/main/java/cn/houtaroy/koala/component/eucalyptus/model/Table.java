package cn.houtaroy.koala.component.eucalyptus.model;

import java.util.Set;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
public interface Table {

    /**
     * 获取表名
     *
     * @return 表名
     */
    String getName();

    /**
     * 获取表的列
     *
     * @return 表的列
     */
    Set<Column> getColumns();
}
