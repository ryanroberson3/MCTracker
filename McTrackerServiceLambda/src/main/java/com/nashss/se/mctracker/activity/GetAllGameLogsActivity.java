package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetAllGameLogsRequest;
import com.nashss.se.mctracker.activity.results.GetAllGameLogsResult;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.models.GameLogModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;



public class GetAllGameLogsActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    /**
     * Instantiates a new GetAllGameLogsActivity object.
     *
     * @param gameLogDao to access the Game Log Dao.
     */
    @Inject
    public GetAllGameLogsActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    /**
     * This method handles the incoming request by getting all the game logs.
     * <p>
     * It then returns all the game logs from a certain user.
     *
     * @param getAllGameLogsRequest request object containing the email to retrieve all the game logs.
     * @return Get all game logs result
     */
    public GetAllGameLogsResult handleRequest(final GetAllGameLogsRequest getAllGameLogsRequest) {
        log.info("Received GetAllGameLogsRequest {}", getAllGameLogsRequest);

        List<GameLog> gameLogs = gameLogDao.getAllGameLogsByEmail(getAllGameLogsRequest.getEmail());
        List<GameLogModel> gameLogModels = new ModelConverter().toGameLogModelList(gameLogs);

        gameLogModels.sort(Comparator.comparing(GameLogModel::getDate).reversed());

        return GetAllGameLogsResult.builder()
                .withGameLogList(gameLogModels)
                .build();
    }
}
