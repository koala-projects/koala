package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;

/**
 * @author Houtaroy
 */
public interface Permission extends Idable<String>, Codeable, Systemic, Deletable {
}
