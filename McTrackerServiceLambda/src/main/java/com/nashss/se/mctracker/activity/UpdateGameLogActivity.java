package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.UpdateGameLogRequest;
import com.nashss.se.mctracker.activity.results.UpdateGameLogResult;
import com.nashss.se.mctracker.converters.LocalDateConverter;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.DateAfterTodayException;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;

public class UpdateGameLogActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a new UpdateGameLogActivity object.
     *
     * @param gameLogDao GameLogDao to access the game log table.
     * @param metricsPublisher MetricsPublisher to publish metrics.
     */
    @Inject
    public UpdateGameLogActivity(GameLogDao gameLogDao, MetricsPublisher metricsPublisher) {
        this.gameLogDao = gameLogDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * This method handles the incoming request by updating a game log.
     * <p>
     * It then returns the updated game log.
     *
     * @param updateGameLogRequest request object containing the email, gameId, date, outcome,
     *                             aspect, hero and villain to update game log.
     * @return Update game log result.
     */
    public UpdateGameLogResult handleRequest(final UpdateGameLogRequest updateGameLogRequest) {
        log.info("Received Update game log request {}", updateGameLogRequest);

        LocalDateConverter converter = new LocalDateConverter();

        GameLog gameLog = gameLogDao.getGameLogById(updateGameLogRequest.getEmail(), updateGameLogRequest.getGameId());
        LocalDate updatedDate = converter.unconvert(updateGameLogRequest.getDate());
        String updatedOutcomeWL = updateGameLogRequest.getOutcomeWL();
        List<String> updatedAspect = updateGameLogRequest.getAspect();
        List<String> updatedHeroes = updateGameLogRequest.getHeroes();
        String updatedVillain = updateGameLogRequest.getVillain();

        if (updatedDate.isAfter(LocalDate.now())) {
            throw new DateAfterTodayException("You can't update a GameLog that hasn't happened yet! Change the date.");
        }

        gameLog.setDate(updatedDate);
        gameLog.setOutcomeWL(updatedOutcomeWL);
        gameLog.setAspect(updatedAspect);
        gameLog.setHeroes(updatedHeroes);
        gameLog.setVillain(updatedVillain);
        gameLog = gameLogDao.saveGameLog(gameLog);

        return UpdateGameLogResult.builder()
                .withGameLog(new ModelConverter().toGameLogModel(gameLog))
                .build();
    }
}
