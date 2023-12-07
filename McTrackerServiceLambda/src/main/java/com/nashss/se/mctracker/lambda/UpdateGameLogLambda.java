package com.nashss.se.mctracker.lambda;

import com.nashss.se.mctracker.activity.requests.UpdateGameLogRequest;
import com.nashss.se.mctracker.activity.results.UpdateGameLogResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateGameLogLambda
        extends LambdaActivityRunner<UpdateGameLogRequest, UpdateGameLogResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateGameLogRequest>, LambdaResponse> {


    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateGameLogRequest> input, Context context) {
        return super.runActivity(() -> {
            UpdateGameLogRequest unauthenticatedRequest = input.fromPath(path ->
                    UpdateGameLogRequest.builder()
                            .withGameId(path.get("gameId"))
                            .build());
            UpdateGameLogRequest bodyRequest = input.fromBody(UpdateGameLogRequest.class);
            return input.fromUserClaims(claims ->
                    UpdateGameLogRequest.builder()
                            .withGameId(unauthenticatedRequest.getGameId())
                            .withEmail(claims.get("email"))
                            .withDate(bodyRequest.getDate())
                            .withOutcomeWL(bodyRequest.getOutcomeWL())
                            .withAspect(bodyRequest.getAspect())
                            .withHeroes(bodyRequest.getHeroes())
                            .withVillain(bodyRequest.getVillain())
                            .build());
            }, (request, serviceComponent) ->
                serviceComponent.provideUpdateGameLogActivity().handleRequest(request)
        );
    }
}
