package com.nashss.se.mctracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.mctracker.activity.requests.DeleteGameLogRequest;
import com.nashss.se.mctracker.activity.results.DeleteGameLogResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteGameLogLambda
        extends LambdaActivityRunner<DeleteGameLogRequest, DeleteGameLogResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteGameLogRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteGameLogRequest> input, Context context) {
        log.info("handleRequest from DeleteGameLogLambda");
        return super.runActivity(
                () -> {
                    DeleteGameLogRequest deleteRequest = input.fromPath(path ->
                            DeleteGameLogRequest.builder()
                                    .withGameId(path.get("gameId"))
                                    .build());
                    return input.fromUserClaims(claims ->
                            DeleteGameLogRequest.builder()
                                    .withEmail(claims.get("email"))
                                    .withGameId(deleteRequest.getGameId())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteGameLogActivity().handleRequest(request)
        );
    }
}
