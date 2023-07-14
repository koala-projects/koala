package cn.koala.postoffice.support;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * 邮件驿站
 *
 * @author Houtaroy
 */
public class SimpleMailPostOffice extends AbstractObjectMapperPostOffice<SimpleMailMessage> {

  public SimpleMailPostOffice(MailSender mailSender) {
    super("simple-mail", SimpleMailMessage.class, mailSender::send);
  }
}
