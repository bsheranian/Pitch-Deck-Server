package lambda;

import exception.DAOException;
import request.CreateTeamSlideRequest;
import result.Result;
import service.CreateTeamSlideService;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class CreateTeamSlideHandler implements RequestHandler<CreateTeamSlideRequest, Result> {

    @Override
    public Result handleRequest(CreateTeamSlideRequest request, Context context) throws DAOException {
        LambdaLogger logger = context.getLogger();
        logger.log("Entering CreateTeamSlideHandler\n");
        return new CreateTeamSlideService().doRequest(request);
    }
}
