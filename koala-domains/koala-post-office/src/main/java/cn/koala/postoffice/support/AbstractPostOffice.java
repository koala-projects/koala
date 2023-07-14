package cn.koala.postoffice.support;

import cn.koala.postoffice.Courier;
import cn.koala.postoffice.Packer;
import cn.koala.postoffice.PostOffice;

import java.util.Map;

/**
 * 驿站抽象类
 * <p>
 * 提取了打包器和信使两个概念, 使职责更加清晰
 *
 * @param <T> 包裹类型
 * @author Houtaroy
 */
public abstract class AbstractPostOffice<T> implements PostOffice {

  protected final String name;

  protected final Packer<T> packer;

  protected final Courier<T> courier;

  public AbstractPostOffice(String name, Packer<T> packer, Courier<T> courier) {
    this.name = name;
    this.packer = packer;
    this.courier = courier;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void post(Map<String, Object> original) throws Exception {
    this.courier.deliver(this.packer.pack(original));
  }
}
