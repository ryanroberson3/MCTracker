package com.nashss.se.mctracker.lambda;

import com.nashss.se.mctracker.activity.requests.ViewAllStatsRequest;
import com.nashss.se.mctracker.activity.results.ViewAllStatsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewAllStatsLambda
        extends LambdaActivityRunner<ViewAllStatsRequest, ViewAllStatsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<ViewAllStatsRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<ViewAllStatsRequest> input, Context context) {
        log.info("handleRequest from View all stats lambda");
        return super.runActivity(() -> input.fromUserClaims(claims ->
                        ViewAllStatsRequest.builder()
                                .withEmail(claims.get("email"))
                                .build()), (request, serviceComponent) ->
                        serviceComponent.provideViewAllStatsActivity().handleRequest(request)
        );
    }
}
