package com.attempt1.app;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendEmail
{

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER = "oleg.petrenko.ig@gmail.com";
    private static final String SMTP_AUTH_PWD  = "dbrjhbcnfqntghbyfqvys8cbvdjksd!Q";

    private static final String emailMsgTxt      = "Online Order Confirmation Message. Also include the Tracking Number.";
    private static final String emailSubjectTxt  = "Testing email!";
    private static final String emailFromAddress = "bijoy.meethal@xxx.com";

    // Add List of Email address to who email needs to be sent to
    private static final String[] emailList = {"kostyanikitchenko@gmail.com"};

    public static void main(String args[]) throws Exception
    {
        SendEmail smtpMailSender = new SendEmail();
        smtpMailSender.postMail( emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress);
        System.out.println("Sucessfully Sent mail to All Users");
    }


    private BodyPart messageBodyPart;

    public void postMail( String recipients[ ], String subject,
                          String message , String from) throws MessagingException
    {
        boolean debug = false;

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);

        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        // new code added
        Multipart multipart = new MimeMultipart();
        // multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename1 = "D:/Auto projects/Last version/attempt1/attempt1/res.txt";
        DataSource source1 = new FileDataSource(filename1);
        messageBodyPart.setDataHandler(new DataHandler(source1));

        messageBodyPart.setFileName("Attachments 1");
        messageBodyPart.setDescription(message);
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        String filename2 = "D:/Auto projects/Last version/attempt1/attempt1/res3.txt";
        DataSource source2 = new FileDataSource(filename2);
        messageBodyPart.setDataHandler(new DataHandler(source2));

        messageBodyPart.setFileName("Attachment 3");
        messageBodyPart.setDescription(message);
        multipart.addBodyPart(messageBodyPart);


        // Put parts in message
        msg.setContent(multipart);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(multipart);
        Transport.send(msg);
    }

    /**
     * SimpleAuthenticator is used to do simple authentication
     * when the SMTP server requires it.
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {

        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

}
