package request;

public class EmailRequest {

    private String sender;
    private String recipient;
    private String subject;
    private String textBody;
    private String htmlBody;

    public EmailRequest() {}

    public EmailRequest(String sender, String recipient, String subject, String textBody, String htmlBody) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.textBody = textBody;
        this.htmlBody = htmlBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

}
