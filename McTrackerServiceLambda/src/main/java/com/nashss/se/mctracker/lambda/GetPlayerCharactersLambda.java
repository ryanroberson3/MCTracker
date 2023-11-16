package com.nashss.se.mctracker.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.mctracker.activity.requests.GetPlayerCharactersRequest;
import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;


public class GetPlayerCharactersLambda
        extends LambdaActivityRunner<GetPlayerCharactersRequest, GetPlayerCharactersResult>
        implements RequestHandler<LambdaRequest<GetPlayerCharactersRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlayerCharactersRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery((query) ->
                        GetPlayerCharactersRequest.builder()
                                .withRole(query.get("role"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetPlayerCharactersActivity().handleRequest(request)
        );
    }

}