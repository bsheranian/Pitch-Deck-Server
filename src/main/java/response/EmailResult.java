package response;

public class EmailResult extends Response {

    private String timestamp; //property for returning the date/time at which the email was sent.

    public EmailResult(boolean success, String message, String timestamp) {
        super(success, message);
        this.timestamp = timestamp;
    }

    public EmailResult() {}

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
