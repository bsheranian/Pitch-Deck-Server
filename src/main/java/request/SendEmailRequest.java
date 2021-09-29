package request;

public class SendEmailRequest {

    private String sender;
    private String[] recipients;
    private String subject;
    private String textBody;
    private String htmlBody;
    private String attachmentFilePath;


    public SendEmailRequest() {}

    public SendEmailRequest(String sender, String[] recipients, String subject, String textBody, String htmlBody, String attachmentFilePath) {
        this.sender = sender;
        this.recipients = recipients;
        this.subject = subject;
        this.textBody = textBody;
        this.htmlBody = htmlBody;
        this.attachmentFilePath = attachmentFilePath;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }

    public void setAttachmentFilePath(String attachmentFilePath) {
        this.attachmentFilePath = attachmentFilePath;
    }
}
