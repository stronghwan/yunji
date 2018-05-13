package com.yunding.news.web.others.LZQ;

//import sun.plugin2.message.Message;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 *  类名称:DoSend
 *
 *  类作用：用于发送邮件给已经注册的用户
 *
 *  启动时间:2018年4月17日
 *
 *  最终修改时间:2018年4月21日
 *
 *  @author 李子琦
 */

public class DoSend {

    // 发件人邮箱地址
    private String from = "13903545636@163.com";
    // 发件人称号，同邮箱地址
    private String user = "13903545636@163.com";
    // 发件人邮箱客户端授权码
    private String password = "lzq111243";

    //发送邮件的方法
    public boolean sendMail(String to, String title, String text){

        Properties props=new Properties();

        props.put("userName","13903545636@163.com");
        // 设置发送邮件的邮件服务器的属性
        props.setProperty("mail.smtp.host", "smtp.163.com");

        // 经过授权，用户名和密码的校验
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // 用刚刚设置好的props对象构建一个session
        // 发送邮件的过程中在console处显示过程信息，供调试
        Session session=Session.getDefaultInstance(props);

        // 用session为参数定义消息对象
        session.setDebug(true);
        // 加载发件人地址
        MimeMessage message=new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(from));

            //为预防网易将邮件当作垃圾邮件进行拦截，先给自己抄送一份
            message.addRecipients(MimeMessage.RecipientType.CC,InternetAddress.parse(props.getProperty("userName")));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //加载标题
            message.setSubject(title);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            //设置交互编码
            contentPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges(); // 保存变化
            Transport transport = session.getTransport("smtp"); // 连接服务器的邮箱
            transport.connect("smtp.163.com", user, password); // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //public static void main(String[] args) { // 做测试用

    //}
}
