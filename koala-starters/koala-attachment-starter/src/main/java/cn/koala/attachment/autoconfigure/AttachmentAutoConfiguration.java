package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.AttachmentApi;
import cn.koala.attachment.AttachmentFacade;
import cn.koala.attachment.AttachmentInitializer;
import cn.koala.attachment.AttachmentListener;
import cn.koala.attachment.AttachmentProperties;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.factory.AttachmentFactory;
import cn.koala.attachment.factory.DefaultAttachmentFactory;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.attachment.storage.LocalAttachmentStorage;
import cn.koala.attachment.storage.MinIOAttachmentStorage;
import cn.koala.attachment.support.DefaultAttachmentApi;
import cn.koala.attachment.support.DefaultAttachmentFacade;
import cn.koala.attachment.support.DefaultAttachmentService;
import io.minio.MinioClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 附件自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(AttachmentProperties.class)
@MapperScan("cn.koala.attachment.repository")
public class AttachmentAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AttachmentFactory attachmentFactory() {
    return new DefaultAttachmentFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty("koala.attachment.root")
  public AttachmentStorage localAttachmentStorage(AttachmentFactory factory, AttachmentProperties properties) {
    return new LocalAttachmentStorage(factory, properties.getRoot());
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(name = "io.minio.MinioClient")
  @ConditionalOnProperty(prefix = "koala.attachment", name = "type", havingValue = "minio")
  public AttachmentStorage minIOAttachmentStorage(AttachmentFactory factory,
                                                  ObjectProvider<MinioClient> clientProvider) throws Exception {
    MinioClient client = clientProvider.getIfAvailable();
    if (client == null) {
      throw new Exception("未找到MinIO客户端, 请检查相关配置");
    }
    return new MinIOAttachmentStorage(factory, client);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentService")
  public AttachmentService attachmentService(AttachmentRepository repository) {
    return new DefaultAttachmentService(repository);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentFacade")
  public AttachmentFacade attachmentFacade(AttachmentService service, AttachmentStorage storage) {
    return new DefaultAttachmentFacade(service, storage);
  }

  @Bean
  @ConditionalOnMissingBean
  public AttachmentApi attachmentApi(AttachmentFacade facade) {
    return new DefaultAttachmentApi(facade);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentListener")
  public AttachmentListener attachmentListener(AttachmentRepository repository, AttachmentStorage storage) {
    return new AttachmentListener(repository, storage);
  }

  @Bean
  public AttachmentInitializer attachmentModuleInitializer() {
    return new AttachmentInitializer();
  }
}
