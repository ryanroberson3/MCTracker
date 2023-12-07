package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetGameLogRequest;
import com.nashss.se.mctracker.activity.results.GetGameLogResult;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import com.nashss.se.mctracker.models.GameLogModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class GetGameLogActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    /**
     * Instantiates a new GetGameLogActivity object.
     *
     * @param gameLogDao to access the Game Log Dao.
     */
    @Inject
    public GetGameLogActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    /**
     * This method handles the incoming request by getting a single game log.
     * <p>
     * It then returns a single game log from a certain user.
     *
     * @param gameLogRequest request object containing the email and gameId to retrieve the single game log.
     * @return Get game log result.
     */
    public GetGameLogResult handleRequest(final GetGameLogRequest gameLogRequest) {
        log.info("Received GetGameLogRequest {}", gameLogRequest.getGameId());

        String requestGameId = gameLogRequest.getGameId();
        String requestEmail = gameLogRequest.getEmail();
        GameLog gameLog = gameLogDao.getGameLogById(requestEmail, requestGameId);

        if (gameLog == null) {
            throw new GameNotFoundException("Game with id: " + requestGameId + " not found");
        }

        GameLogModel gameLogModel = new ModelConverter().toGameLogModel(gameLog);

        return GetGameLogResult.builder()
                .withGameLog(gameLogModel)
                .build();
    }

}
