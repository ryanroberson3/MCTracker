package com.nashss.se.mctracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.mctracker.activity.requests.GetAllGameLogsRequest;
import com.nashss.se.mctracker.activity.results.GetAllGameLogsResult;

public class GetAllGameLogsLambda
        extends LambdaActivityRunner<GetAllGameLogsRequest, GetAllGameLogsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetAllGameLogsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetAllGameLogsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromUserClaims(claims ->
                        GetAllGameLogsRequest.builder()
                                .withEmail(claims.get("email"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllGameLogsActivity().handleRequest(request)
        );
    }
}
