package cn.koala.postoffice.support;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;

/**
 * 阿里云短信驿站
 *
 * @author Houtaroy
 */
public class AliyunSmsPostOffice extends AbstractPostOffice<SendSmsRequest> {

  public AliyunSmsPostOffice(Client client) {
    super("aliyun-sms", SendSmsRequest::build, client::sendSms);
  }
}
