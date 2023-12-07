package com.nashss.se.mctracker.lambda;

import com.nashss.se.mctracker.activity.requests.CreateGameLogRequest;
import com.nashss.se.mctracker.activity.results.CreateGameLogResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateGameLogLambda
        extends LambdaActivityRunner<CreateGameLogRequest, CreateGameLogResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateGameLogRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateGameLogRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateGameLogRequest unauthenticatedRequest = input.fromBody(CreateGameLogRequest.class);
            return input.fromUserClaims(claims ->
                    CreateGameLogRequest.builder()
                            .withDate(unauthenticatedRequest.getDate())
                            .withEmail(claims.get("email"))
                            .withAspect(unauthenticatedRequest.getAspect())
                            .withHeroes(unauthenticatedRequest.getHeroes())
                            .withOutcomeWL(unauthenticatedRequest.getOutcomeWL())
                            .withVillain(unauthenticatedRequest.getVillain())
                            .build());
            }, (request, serviceComponent) ->
                serviceComponent.provideCreateGameLogActivity().handleRequest(request)
        );
    }

}

