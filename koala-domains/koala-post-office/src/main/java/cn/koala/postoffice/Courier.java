package cn.koala.postoffice;

/**
 * 信使, 负责包裹的投递
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface Courier<T> {

  void deliver(T parcel) throws Exception;
}
