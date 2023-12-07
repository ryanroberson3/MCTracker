package com.nashss.se.mctracker.lambda;

import com.nashss.se.mctracker.activity.requests.GetGameLogRequest;
import com.nashss.se.mctracker.activity.results.GetGameLogResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetGameLogLambda
     extends LambdaActivityRunner<GetGameLogRequest, GetGameLogResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetGameLogRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetGameLogRequest> input, Context context) {
        log.info("handleRequest from Get Game log LAMBDA");
        return super.runActivity(() -> {
            GetGameLogRequest unauthenticatedRequest = input.fromPath(path ->
                    GetGameLogRequest.builder()
                            .withGameId(path.get("gameId"))
                            .build());
            return input.fromUserClaims(claims ->
                    GetGameLogRequest.builder()
                            .withGameId(unauthenticatedRequest.getGameId())
                            .withEmail(claims.get("email"))
                            .build());
            }, (request, serviceComponent) ->
                serviceComponent.provideGetGameLogActivity().handleRequest(request)
        );
    }
}
