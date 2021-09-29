package result;

public class SendEmailResult extends Result {

    private String timestamp; //property for returning the date/time at which the email was sent.

    public SendEmailResult(boolean success, String message, String timestamp) {
        super(success, message);
        this.timestamp = timestamp;
    }

    public SendEmailResult() {}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
