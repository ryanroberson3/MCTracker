package com.nashss.se.mctracker.dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.nashss.se.mctracker.metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
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
            List<String> characterNames = new ArrayList<>();

//            ItemCollection<QueryOutcome> items = playerCharacterTable.query("role", role);
//
//            for (Item item : items) {
//                String characterName = item.getString("name");
//                characterNames.add(characterName);
//            }

            return characterNames;
        }
    }

