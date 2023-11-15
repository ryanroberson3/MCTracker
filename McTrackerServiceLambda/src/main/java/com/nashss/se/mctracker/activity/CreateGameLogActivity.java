package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.CreateGameLogRequest;
import com.nashss.se.mctracker.activity.results.CreateGameLogResult;
import com.nashss.se.mctracker.converters.LocalDateConverter;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.models.GameLogModel;
import com.nashss.se.mctracker.utils.IdUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Arrays;


public class CreateGameLogActivity {

    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;
    @Inject
    public CreateGameLogActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    public CreateGameLogResult handleRequest(final CreateGameLogRequest createGameLogRequest) {
        log.info("received CreateGameLogRequest {}", createGameLogRequest);

        LocalDateConverter converter = new LocalDateConverter();
        LocalDate date = converter.unconvert(createGameLogRequest.getDate());

        GameLog newGameLog = new GameLog();
        newGameLog.setEmail(createGameLogRequest.getEmail());
        newGameLog.setGameId(IdUtils.generateGameId());
        newGameLog.setDate(date);
        newGameLog.setAspect(createGameLogRequest.getAspect());
        newGameLog.setOutcomeWL(createGameLogRequest.getOutcomeWL());
        newGameLog.setHeroes(createGameLogRequest.getHeroes());
        newGameLog.setVillain(createGameLogRequest.getVillain());

        gameLogDao.saveGameLog(newGameLog);

        GameLogModel gameLogModel = new ModelConverter().toGameLogModel(newGameLog);
        return CreateGameLogResult.builder()
                .withGameLog(gameLogModel)
                .build();
    }

}
