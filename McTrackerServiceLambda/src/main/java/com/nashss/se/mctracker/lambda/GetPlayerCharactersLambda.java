//package com.nashss.se.mctracker.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.nashss.se.mctracker.activity.requests.CreateGameLogRequest;
//import com.nashss.se.mctracker.activity.requests.GetPlayerCharactersRequest;
//import com.nashss.se.mctracker.activity.results.CreateGameLogResult;
//import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;
//import com.nashss.se.mctracker.lambda.AuthenticatedLambdaRequest;
//import com.nashss.se.mctracker.lambda.LambdaActivityRunner;
//import com.nashss.se.mctracker.lambda.LambdaResponse;
//
//public class GetPlayerCharactersLambda
//        extends LambdaActivityRunner<GetPlayerCharactersRequest, GetPlayerCharactersResult>
//        implements RequestHandler<AuthenticatedLambdaRequest<GetPlayerCharactersRequest>, LambdaResponse> {
//
//    @Override
//    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPlayerCharactersRequest> input, Context context) {
//        return super.runActivity(
//                () -> {
//                    GetPlayerCharactersRequest unauthenticatedRequest =
//                    return input.fromUserClaims(claims ->
//                            GetPlayerCharactersRequest.builder()
//                                    .withRole(unauthenticatedRequest.getRole())
//                                    .build());
//                },
//                (request, serviceComponent) ->
//                        serviceComponent.provideCreateGameLogActivity().handleRequest(request)
//        );
//    }
//
//}