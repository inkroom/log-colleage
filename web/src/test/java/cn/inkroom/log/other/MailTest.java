/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.other;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.mail.*;
import io.vertx.ext.stomp.*;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

/**
 * @author 墨盒
 * @date 19-2-23
 */
@Ignore
public class MailTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testMail() {

        Vertx vertx = Vertx.vertx();


        StompServer server = StompServer.create(vertx, new StompServerOptions().setPort(1234).setHost("0.0.0.0"))
                .handler(StompServerHandler.create(vertx)).listen(new Handler<AsyncResult<StompServer>>() {
                    @Override
                    public void handle(AsyncResult<StompServer> stompServerAsyncResult) {
                        if (stompServerAsyncResult.succeeded()) {
                            logger.info("开始监听");
                        }
                    }
                });

        MailMessage mail = new MailMessage();
        mail.setFrom("error@inkroom.cn").setTo("245900986@qq.com").setText("某个服务挂了").setBounceAddress("inkroom@inkroom.cn");
//
        StompClient client = StompClient.create(vertx);
        client.connect(1234, "locahost", new Handler<AsyncResult<StompClientConnection>>() {
            @Override
            public void handle(AsyncResult<StompClientConnection> stompClientConnectionAsyncResult) {
            }
        }).writingFrameHandler(new Handler<Frame>() {
            @Override
            public void handle(Frame frame) {
            }
        });
        MailConfig config = new MailConfig();
        config.setHostname("localhost");
        config.setPort(1234);
        config.setStarttls(StartTLSOptions.REQUIRED);
        config.setKeepAlive(true);
//        config.setUsername("user");
//        config.setPassword("password");
        MailClient mailClient = MailClient.createNonShared(vertx, config);

        mailClient.sendMail(mail, new Handler<AsyncResult<MailResult>>() {
            @Override
            public void handle(AsyncResult<MailResult> mailResultAsyncResult) {
                if (mailResultAsyncResult.succeeded()) {
                    logger.debug("发送成功");
                } else {
                    logger.error("", mailResultAsyncResult.cause());
                }
            }
        });
        try {
            Thread.sleep(1001000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        vertx.createSharedWorkerExecutor("vertx.mail");
//
//        MailService service = MailService.createEventBusProxy(vertx, "vertx.mail");
//        MailMessage mail = new MailMessage();
//        mail.setFrom("error@inkroom.cn").setTo("245900986@qq.com").setText("某个服务挂了").setBounceAddress("inkroom@inkroom.cn");
//
//
//        service.sendMail(mail, mailResultAsyncResult -> {
//            if (mailResultAsyncResult.succeeded()) {
//                logger.info("发送成功");
//            } else {
//                logger.error("", mailResultAsyncResult.cause());
//            }
//        });


    }
    @Test
    public void testFast() throws Exception{
        // DNS服务器，看看本机的DNS配置
        String dns = "dns://8.8.8.8";
        String email = "inkroom@qq.com";
        String domain = email.substring(email.indexOf('@') + 1);
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        env.put(Context.PROVIDER_URL, dns);
        DirContext ctx = new InitialDirContext(env);
        Attributes attr = ctx.getAttributes(domain, new String[] { "MX" });
        NamingEnumeration servers = attr.getAll();

        // 列出所有邮件服务器：
        while (servers.hasMore()) {
            Attribute hosts = (Attribute) servers.next();
            for (int i = 0; i < hosts.size(); i++) {
                String host = (String) hosts.get(i);
                host = host.substring(host.indexOf(' ') + 1);
                System.out.print("发送邮件 " + host + " ...");
                sendMail(host, email);
                System.out.println("OK");
            }
        }
    }
    protected static void sendMail(String smtpHost, String email)
            throws MessagingException {

        Properties mailProperties = System.getProperties();
        mailProperties.put("mail.smtp.host", smtpHost);
        mailProperties.put("mail.smtp.port", "25");
        mailProperties.put("mail.smtp.auth", "false");
        Session mailSession = Session.getInstance(mailProperties, null);
        MimeMessage mailMessage = new MimeMessage(mailSession);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart("related");
        messageBodyPart.setText("邮件");

        multipart.addBodyPart(messageBodyPart);
        mailMessage.setContent(multipart);
        mailMessage.setSentDate(new Date());
        mailMessage.setFrom(new InternetAddress("javayou@qq.com"));
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
                email));
        mailMessage.setSubject("hi，邮件发送测试");
        Transport.send(mailMessage);
    }
}
