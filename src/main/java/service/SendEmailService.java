package service;


import com.amazonaws.regions.Regions;
import request.SendEmailRequest;
import result.SendEmailResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

import java.sql.Timestamp;

public class SendEmailService extends ServiceTemplate<SendEmailRequest, SendEmailResult> {


    @Override
    public SendEmailResult doRequest(SendEmailRequest request) {

        String timestamp = new Timestamp(new Date().getTime()).toString();

        String subject = request.getSubject();
        String textBody = request.getTextBody();
        String bodyHtml = request.getHtmlBody();
        String attachmentFilePath = request.getAttachmentFilePath();
        String sender = request.getSender();
        String[] recipients = request.getRecipients();

        try {
            sendEmail(subject, sender, recipients, textBody, bodyHtml, attachmentFilePath);
        } catch (MessagingException | IOException e) {
            return new SendEmailResult(false, "Email failed to send: " + e.getMessage(), timestamp);
        }

        return new SendEmailResult(true, "Email successfully sent!", timestamp);
    }







    private void sendEmail(String subject, String sender, String[] recipients, String bodyText, String bodyHtml,
                           String attachmentFilePath) throws MessagingException, IOException {

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        // Add subject, from and to lines.
        message.setSubject(subject, "UTF-8");
        message.setFrom(new InternetAddress(sender));
        for (String toMail : recipients) {
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toMail));
        }

        // Create a multipart/alternative child container.
        MimeMultipart msg_body = new MimeMultipart("alternative");

        // Create a wrapper for the HTML and text parts.
        MimeBodyPart wrap = new MimeBodyPart();

        // Define the text part.
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(bodyText, "text/plain; charset=UTF-8");

        // Define the HTML part.
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(bodyHtml, "text/html; charset=UTF-8");

        // Add the text and HTML parts to the child container.
        msg_body.addBodyPart(textPart);
        msg_body.addBodyPart(htmlPart);

        // Add the child container to the wrapper object.
        wrap.setContent(msg_body);

        // Create a multipart/mixed parent container.
        MimeMultipart msg = new MimeMultipart("mixed");

        // Add the parent container to the message.
        message.setContent(msg);

        // Add the multipart/alternative part to the message.
        msg.addBodyPart(wrap);

        // Define the attachment
        MimeBodyPart att = new MimeBodyPart();
        DataSource fds = new FileDataSource(attachmentFilePath);
        att.setDataHandler(new DataHandler(fds));
        att.setFileName(fds.getName());

        // Add the attachment to the message.
        msg.addBodyPart(att);



        System.out.println("Attempting to send an email through Amazon SES "
                +"using the AWS SDK for Java...");

        // Instantiate an Amazon SES client, which will make the service
        // call with the supplied AWS credentials.
        AmazonSimpleEmailService client =
                AmazonSimpleEmailServiceClientBuilder.standard()
                        // Replace US_WEST_2 with the AWS Region you're using for
                        // Amazon SES.
                        .withRegion(Regions.US_WEST_2).build();

        // Print the raw email content on the console
        PrintStream out = System.out;
        message.writeTo(out);

        // Send the email.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);
        RawMessage rawMessage =
                new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

        SendRawEmailRequest rawEmailRequest =
                new SendRawEmailRequest(rawMessage);

        client.sendRawEmail(rawEmailRequest);
        System.out.println("Email sent!");
    }
}




