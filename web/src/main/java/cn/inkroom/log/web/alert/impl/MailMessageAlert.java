package cn.inkroom.log.web.alert.impl;

import cn.inkroom.log.web.alert.MessageAlert;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.Properties;
import java.util.function.BiConsumer;

/**
 * @author 墨盒
 * @date 19-5-6
 */
public class MailMessageAlert implements MessageAlert {

    private String from;
    private String to;
    private String smtp;
    private String username;
    private String password;
    private Mailer mailer;

    @Override
    public void init(Properties properties) {

        //TODO 优化参数注入方式
        properties.forEach((key, value) -> {
            String k = ((String) key);
            if (k.contains("mail")) {
                if (k.endsWith("from")) {
                    this.from = (String) value;
                } else if (k.endsWith("to")) {
                    this.to = (String) value;
                } else if (k.endsWith("smtp")) {
                    this.smtp = (String) value;
                } else if (k.endsWith("username")) {
                    this.username = (String) value;
                } else if (k.endsWith("password")) {
                    this.password = (String) value;
                }

            }
        });
        mailer = MailerBuilder.
                withSMTPServer(smtp.substring(0, smtp.indexOf(":")),
                        Integer.parseInt(smtp.substring(smtp.indexOf(":") + 1))
                        , username, password)
                .buildMailer();
    }

    @Override
    public boolean alert(String message) {
        Email email = EmailBuilder.startingBlank().from(from)
                .to(to)
                .withPlainText(message)
                .buildEmail();

        //TODO 待修复无法获取发送结果问题
        mailer.sendMail(email);
        return true;
    }
}
