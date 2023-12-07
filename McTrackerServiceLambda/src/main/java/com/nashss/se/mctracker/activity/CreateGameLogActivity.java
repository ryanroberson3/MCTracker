package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.CreateGameLogRequest;
import com.nashss.se.mctracker.activity.results.CreateGameLogResult;
import com.nashss.se.mctracker.converters.LocalDateConverter;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.DateAfterTodayException;
import com.nashss.se.mctracker.models.GameLogModel;
import com.nashss.se.mctracker.utils.IdUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import javax.inject.Inject;


public class CreateGameLogActivity {

    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    /**
     * CreateGameLogActivity Constructor.
     *
     * @param gameLogDao GameLogDao to access the game log dao.
     */
    @Inject
    public CreateGameLogActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    /**
     * This method handles the incoming request for creating a game log.
     *
     * @param createGameLogRequest request object containing the date, aspect,
     *                             outcome, hero and villain to create a game log.
     * @return GameLogModel
     */
    public CreateGameLogResult handleRequest(final CreateGameLogRequest createGameLogRequest) {
        log.info("received CreateGameLogRequest {}", createGameLogRequest);

        LocalDateConverter converter = new LocalDateConverter();
        LocalDate date = converter.unconvert(createGameLogRequest.getDate());

        if (date.isAfter(LocalDate.now())) {
            throw new DateAfterTodayException("You can't make a GameLog that hasn't happened yet! Change the date.");
        }

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
