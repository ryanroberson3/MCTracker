package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.DeleteGameLogRequest;
import com.nashss.se.mctracker.activity.results.DeleteGameLogResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteGameLogActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    @Inject
    public DeleteGameLogActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    public DeleteGameLogResult handleRequest(final DeleteGameLogRequest deleteGameLogRequest) {
        log.info("Received deleteGameLogRequest {}", deleteGameLogRequest);
        String email = deleteGameLogRequest.getEmail();
        String gameId = deleteGameLogRequest.getGameId();

        String result = gameLogDao.deleteGameLog(email, gameId);

        return new DeleteGameLogResult.Builder()
                .withDeleteResult(result)
                .build();
    }
}
