package com.airline.utils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmailWithAttachment(String toEmail, String subject, String body, String filePath) {
        final String fromEmail = "deepujagana2002@gmail.com";  // replace
        final String password = "zysk ejvk wrxk uxpz";      // Gmail requires App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach PDF
            if (filePath != null) {
                MimeBodyPart attachPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachPart.setDataHandler(new DataHandler(source));
                attachPart.setFileName(filePath);
                multipart.addBodyPart(attachPart);
            }

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("✅ Email sent successfully with ticket attached!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}