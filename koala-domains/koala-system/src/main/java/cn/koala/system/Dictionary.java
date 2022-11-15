package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;

/**
 * 字典
 *
 * @author Houtaroy
 */
public interface Dictionary extends Idable<String>, Codeable, Systemic, Deletable {
}
