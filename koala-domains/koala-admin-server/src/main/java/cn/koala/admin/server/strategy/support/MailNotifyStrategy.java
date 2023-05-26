package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.support.SimpleStatusChangeMessageFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 邮件通知策略
 *
 * @author Houtaroy
 */
@Slf4j
public class MailNotifyStrategy extends AbstractNotifyStrategy {

  private final MailSender mailSender;

  @Getter
  private final String from;

  @Getter
  private final String subject;

  public MailNotifyStrategy(@NonNull MailSender mailSender, @Nullable String from, @NonNull String subject) {
    super(new SimpleStatusChangeMessageFactory());
    Assert.notNull(mailSender, "[koala-admin-server]: 请参照文档配置邮件发送器");
    Assert.hasText(subject, "[koala-admin-server]: 邮件主题不能为空");
    this.mailSender = mailSender;
    String actualFrom = obtainFrom(mailSender, from);
    Assert.hasText(actualFrom, "[koala-admin-server]: 请参照文档配置发送邮箱地址");
    this.from = actualFrom;
    this.subject = subject;
  }

  /**
   * 获取发送邮箱地址
   * <p>若未进行手动配置, 则尝试获取邮件发送器的用户名属性
   *
   * @param mailSender 邮件发送器
   * @param from       手动配置发送邮箱地址参数
   * @return 发送邮箱地址
   */
  @Nullable
  protected String obtainFrom(@NonNull MailSender mailSender, @Nullable String from) {
    if (StringUtils.hasText(from)) {
      return from;
    }
    if (mailSender instanceof JavaMailSenderImpl impl) {
      return impl.getUsername();
    }
    return null;
  }

  @Override
  public String getName() {
    return "mail";
  }

  @Override
  protected boolean doNotify(Maintainer maintainer, String message) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(getFrom());
    mailMessage.setTo(maintainer.getEmail());
    mailMessage.setSubject(getSubject());
    mailMessage.setText(message);
    try {
      this.mailSender.send(mailMessage);
      return true;
    } catch (MailException e) {
      LOGGER.error("[koala-admin-server]: 发送邮件消息[{}]给[{}]失败", message, maintainer.getEmail(), e);
      return false;
    }
  }
}
