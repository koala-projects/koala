package cn.koala.ocr.support;

import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.ZooModel;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 预测器池工厂
 *
 * @author Houtaroy
 */
public class PredictorPoolFactory {

  public static <I, O> ObjectPool<Predictor<I, O>> create(ZooModel<I, O> model) {
    GenericObjectPoolConfig<Predictor<I, O>> config = new GenericObjectPoolConfig<>();
    config.setMaxIdle(Runtime.getRuntime().availableProcessors());
    return new GenericObjectPool<>(new SimplePredictorPooledObjectFactory<>(model), config);
  }

  public static class SimplePredictorPooledObjectFactory<I, O> extends BasePooledObjectFactory<Predictor<I, O>> {

    private final ZooModel<I, O> model;

    public SimplePredictorPooledObjectFactory(ZooModel<I, O> model) {
      this.model = model;
    }

    @Override
    public Predictor<I, O> create() {
      return this.model.newPredictor();
    }

    @Override
    public PooledObject<Predictor<I, O>> wrap(Predictor<I, O> obj) {
      return new DefaultPooledObject<>(obj);
    }
  }
}
