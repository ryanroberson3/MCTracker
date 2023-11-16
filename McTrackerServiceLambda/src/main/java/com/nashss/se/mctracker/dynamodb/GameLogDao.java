package com.nashss.se.mctracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import com.nashss.se.mctracker.metrics.MetricsConstants;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class GameLogDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;
    @Inject
    public GameLogDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public GameLog saveGameLog(GameLog newGameLog) {
        this.dynamoDBMapper.save(newGameLog);
        return newGameLog;
    }

    public GameLog getGameLogById(String email, String gameId) {
        GameLog gameLog = dynamoDBMapper.load(GameLog.class, email, gameId);
        if (gameLog == null) {
            metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 1);
            throw new GameNotFoundException("Game not found");
        }
        metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 0);
        return gameLog;
    }

    public List<GameLog> getAllGameLogsByEmail(String email) {
        GameLog gameLog = new GameLog();
        gameLog.setEmail(email);

        DynamoDBQueryExpression<GameLog> queryExpression = new DynamoDBQueryExpression<GameLog>()
                .withHashKeyValues(gameLog);

        List<GameLog> gameLogList = dynamoDBMapper.query(GameLog.class, queryExpression);

        if (gameLogList == null || gameLogList.isEmpty()) {
            metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 1);
            throw new GameNotFoundException("The GameLog list is empty or null");
        }

        metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 0);
        return gameLogList;
    }

}
