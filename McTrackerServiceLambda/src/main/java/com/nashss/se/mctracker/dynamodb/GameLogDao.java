package com.nashss.se.mctracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

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

}
