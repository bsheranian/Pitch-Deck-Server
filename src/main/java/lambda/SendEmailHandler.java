package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import request.SendEmailRequest;
import result.SendEmailResult;
import service.SendEmailService;

public class SendEmailHandler implements RequestHandler<SendEmailRequest, SendEmailResult> {

    @Override
    public SendEmailResult handleRequest(SendEmailRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Entering SendEmailHandler");
        return new SendEmailService().doRequest(request);
    }
}
