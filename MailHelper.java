package com.ust.allowance.util;

import com.sun.mail.smtp.SMTPTransport;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper
{

  public  boolean sendEmail(String emailAddress, String subject, String message)
  {
    if (isValidEmailAddress(emailAddress)){
      try{
        Properties props = System.getProperties();
        props.put("mail.smtp.host", ("exchange.heb.com"));
        props.put("mail.smtp.localhost",("exchange.heb.com"));
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage msg = new MimeMessage(session);
        msg.setHeader("X-Mailer", "smtpsend");
        msg.setSentDate(Calendar.getInstance().getTime());
        msg.setFrom(new InternetAddress(("manohar.hareesh@heb.com")));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress, false));
        msg.setSubject(subject);
        msg.setText(message);
        SMTPTransport transport = (SMTPTransport)session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
      }
      catch (Exception e){
        return false;
      }
      return true;
    }
   
    return false;
  }
  
  private static boolean isValidEmailAddress(String _email)
  {
    return (_email != null) && (_email.trim().length() > 5) && (_email.indexOf('@') > -1);
  }
  
  
}
