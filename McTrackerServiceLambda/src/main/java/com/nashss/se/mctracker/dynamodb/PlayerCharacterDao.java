package com.nashss.se.mctracker.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.*;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.mctracker.dynamodb.models.PlayerCharacter;
import com.nashss.se.mctracker.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class PlayerCharacterDao {
    private final DynamoDBMapper dynamoDbMapper;
    private final MetricsPublisher metricsPublisher;
        @Inject
        public PlayerCharacterDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
            this.dynamoDbMapper = dynamoDBMapper;
            this.metricsPublisher = metricsPublisher;
        }

    public List<String> getCharactersByRole(String role) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":role", new AttributeValue().withS(role));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("role = :role")
                .withExpressionAttributeValues(valueMap);

        PaginatedScanList<PlayerCharacter> characterNames = dynamoDbMapper.scan(PlayerCharacter.class, scanExpression);

        List<String> roleList = new ArrayList<>();

        for (PlayerCharacter character : characterNames) {
            String characterRole = character.getName();
            roleList.add(characterRole);
        }

        return roleList;
    }
    }

