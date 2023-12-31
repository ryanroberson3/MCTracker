package com.nashss.se.mctracker.dynamodb;

import com.nashss.se.mctracker.dynamodb.models.PlayerCharacter;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerCharacterDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a PlayerCharacterDao object.
     *
     * @param dynamoDBMapper mapper for dynamoDB.
     * @param metricsPublisher to record metrics.
     */
    @Inject
    public PlayerCharacterDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDbMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Gets player characters by role.
     *
     * @param role Role to pass in to get the player characters.
     * @return The list of player characters by role.
     */
    public List<String> getCharactersByRole(String role) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":role", new AttributeValue().withS(role));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("characterRole = :role")
                .withExpressionAttributeValues(valueMap);

        PaginatedScanList<PlayerCharacter> characterNames = dynamoDbMapper.scan(PlayerCharacter.class, scanExpression);

        List<String> roleList = new ArrayList<>();

        for (PlayerCharacter character : characterNames) {
            String characterRole = character.getName();
            roleList.add(characterRole);
        }

        Collections.sort(roleList);

        return roleList;
    }
}

