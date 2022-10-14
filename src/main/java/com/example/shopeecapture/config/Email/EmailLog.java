package com.example.shopeecapture.config.Email;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class EmailLog {

//    EmailMessageBean emailMessageBean = new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(),("responseInfo:"+responseInfo.getResponseMsg().toString()));
//            emailLog.log(emailMessageBean);
//            emailLog.sendEmail();

    List<EmailMessageBean> contentList = new ArrayList<>();

    public void log(EmailMessageBean emailMessageBean){
        this.contentList.add(emailMessageBean);
    }

    public void sendEmail(String subject){
        String emailContents = generateHtml(contentList);

        Properties props = new Properties();

        // 设置邮箱服务器的Properties
        props.put("mail.smtp.host", "smtp.qq.com");
        // 设置通过服务器认证
        props.put("mail.smtp.auth", "true");

        // 创建一个 session
        Session session = javax.mail.Session.getInstance(props);

        // 开启debug模式 可以logcat上看发件时的信息
        session.setDebug(false);

        MimeMessage message = new MimeMessage(session);
        try {
            // 设置发件地址
            message.setFrom(new InternetAddress("398005033@qq.com"));
            // 设置接收地址
            //多个收件地址
            String[] eachRecipients = null;
            eachRecipients = "otto.ou.oyylwy@gmail.com".split("\\|");
            for(int i=0;i<eachRecipients.length;i++){
                String recipient = eachRecipients[i];
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            // 设置邮件标题
            message.setSubject(StringUtils.isBlank(subject)?"application log":subject);
            // 设置邮件内容
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String emailContentText = generateHtml(contentList);
            messageBodyPart.setContent(emailContentText, "text/html;charset=UTF-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setHeader("Content-Encoding","UTF-8");
            message.setContent(multipart, "text/html;charset=UTF-8");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            // 连接服务器
            transport.connect("smtp.qq.com", "398005033@qq.com", "qdfnbrtcpqgtbifi");
            // 发送
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.contentList = new ArrayList<>();
    }

    /**
     * 生成html内容
     * @param contentList 写入表格的内容，第一行作为表头
     * @return
     */
    public String generateHtml(List<EmailMessageBean> contentList) {
        StringBuilder stb = new StringBuilder();
        stb.append("<!DOCTYPE html>")
                .append("<html lang='zh'>")
                .append(this.generateHtmlHead())
                .append(this.generateHtmlBody(contentList))
                .append("</html>");
        return stb.toString();
    }

    /**
     * 生成html head标签里面的内容
     * @return
     */
    public String generateHtmlHead() {
        return "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <title>title</title>"
                + "    <meta name='description' content='log message'>"
                + "</head>";
    }

    /**
     * 生成body标签里面的内容
     * @param contentList 第一行数据默认作为表头字段
     * @return
     */
    public String generateHtmlBody(List<EmailMessageBean> contentList) {
        StringBuilder stb = new StringBuilder();
        stb.append("<body>")
            .append("<table style='font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;' border='1'>")
                .append("<tr style='background-color:#ffffff;'>")
                    .append("<th style='font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;'>TimeStamp</th>")
                    .append("<th style='font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;'>ThreadName</th>")
                    .append("<th style='font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;'>ClassName</th>")
                    .append("<th style='font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;'>Message</th>")
                .append("</tr>");
            for (EmailMessageBean emailMessageBean : contentList){
                stb.append("<tr style='background-color:#ffffff;'>")
                    .append("<td style='font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;word-break: break-all; word-wrap:break-word;'>"+emailMessageBean.getTimeStamp()+"</td>")
                    .append("<td style='font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;word-break: break-all; word-wrap:break-word;'>"+emailMessageBean.getThreadName()+"</td>")
                    .append("<td style='font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;word-break: break-all; word-wrap:break-word;'>"+emailMessageBean.getClassName()+"</td>")
                    .append("<td style='font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;word-break: break-all; word-wrap:break-word;'>"+emailMessageBean.getMessage()+"</td>")
                .append("</tr>");
            }
            stb.append("</table>")
        .append("</body>");
        return stb.toString();
    }
}