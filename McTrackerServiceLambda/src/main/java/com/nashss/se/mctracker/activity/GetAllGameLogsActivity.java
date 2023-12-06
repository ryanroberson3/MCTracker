package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetAllGameLogsRequest;
import com.nashss.se.mctracker.activity.results.GetAllGameLogsResult;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.models.GameLogModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GetAllGameLogsActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    @Inject
    public GetAllGameLogsActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }


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
