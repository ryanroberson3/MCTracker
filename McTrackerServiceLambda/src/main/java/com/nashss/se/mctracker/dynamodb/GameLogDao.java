package com.nashss.se.mctracker.dynamodb;

import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import com.nashss.se.mctracker.metrics.MetricsConstants;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class GameLogDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a GameLogDao object.
     *
     * @param dynamoDBMapper mapper for dynamoDB.
     * @param metricsPublisher to record metrics.
     */
    @Inject
    public GameLogDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Saves a gameLog.
     *
     * @param newGameLog The gameLog to save.
     * @return The gameLog that was saved.
     */
    public GameLog saveGameLog(GameLog newGameLog) {
        this.dynamoDBMapper.save(newGameLog);
        return newGameLog;
    }

    /**
     * Gets a single gameLog.
     *
     * @param email The email for getting the gameLog.
     * @param gameId The gameId for getting the gameLog
     * @return The gameLog that was received.
     */
    public GameLog getGameLogById(String email, String gameId) {
        GameLog gameLog = dynamoDBMapper.load(GameLog.class, email, gameId);
        if (gameLog == null) {
            metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 1);
            throw new GameNotFoundException("Game not found");
        }
        metricsPublisher.addCount(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT, 0);
        return gameLog;
    }

    /**
     * Gets all gameLogs.
     *
     * @param email The email for getting the gameLog.
     * @return The list of gameLogs that was received.
     */
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

    /**
     * Deletes a gameLog.
     *
     * @param email The email for deleting the gameLog.
     * @param gameId The gameId for deleting the gameLog
     * @return A String that says the gameLog was defeated.
     */
    public String deleteGameLog(String email, String gameId) {
        GameLog gameToDelete = new GameLog();

        gameToDelete.setEmail(email);
        gameToDelete.setGameId(gameId);

        dynamoDBMapper.delete(gameToDelete);

        metricsPublisher.addCount(MetricsConstants.DELETEGAMELOG_GAMENOTFOUND_COUNT, 0);
        return String.format("GameLog, %s, successfully deleted", gameId);
    }

    /**
     * View all stats for a user's gameLogs.
     *
     * @param email The email for getting the gameLog.
     * @return The list of stats for a user.
     */
    public List<String> viewAllStats(String email) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<GameLog> query = new DynamoDBQueryExpression<GameLog>()
                .withIndexName("TotalWLIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(map);

        PaginatedQueryList<GameLog> outcomes = dynamoDBMapper.query(GameLog.class, query);

        return outcomes.stream()
                .map(GameLog::getOutcomeWL)
                .collect(Collectors.toList());
    }


}
