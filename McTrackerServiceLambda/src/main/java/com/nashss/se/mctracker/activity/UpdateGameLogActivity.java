package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.UpdateGameLogRequest;
import com.nashss.se.mctracker.activity.results.UpdateGameLogResult;
import com.nashss.se.mctracker.converters.LocalDateConverter;
import com.nashss.se.mctracker.converters.ModelConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class UpdateGameLogActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UpdateGameLogActivity(GameLogDao gameLogDao, MetricsPublisher metricsPublisher) {
        this.gameLogDao = gameLogDao;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdateGameLogResult handleRequest(final UpdateGameLogRequest updateGameLogRequest) {
        log.info("Received Update game log request {}", updateGameLogRequest);

        LocalDateConverter converter = new LocalDateConverter();

        GameLog gameLog = gameLogDao.getGameLogById(updateGameLogRequest.getEmail(), updateGameLogRequest.getGameId());
        LocalDate updatedDate = converter.unconvert(updateGameLogRequest.getDate());
        String updatedOutcomeWL = updateGameLogRequest.getOutcomeWL();
        List<String> updatedAspect = updateGameLogRequest.getAspect();
        List<String> updatedHeroes = updateGameLogRequest.getHeroes();
        String updatedVillain = updateGameLogRequest.getVillain();

        gameLog.setDate(updatedDate);
        gameLog.setOutcomeWL(updatedOutcomeWL);
        gameLog.setAspect(updatedAspect);
        gameLog.setHeroes(updatedHeroes);
        gameLog.setVillain(updatedVillain);

        return UpdateGameLogResult.builder()
                .withGameLog(new ModelConverter().toGameLogModel(gameLog))
                .build();
    }
}
