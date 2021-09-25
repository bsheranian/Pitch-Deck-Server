package lambda;

import request.CreateTeamSlideRequest;
import service.CreateTeamSlideService;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class CreateTeamSlideHandler implements RequestHandler<CreateTeamSlideRequest, Void> {

    @Override
    public Void handleRequest(CreateTeamSlideRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Entering CreateTeamSlideHandler");
        return new CreateTeamSlideService().doRequest(request);
    }
}
