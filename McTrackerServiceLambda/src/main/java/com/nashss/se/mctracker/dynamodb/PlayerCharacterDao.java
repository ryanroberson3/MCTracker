package com.nashss.se.mctracker.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Singleton
public class PlayerCharacterDao {
        private final Table playerCharacterTable;
        @Inject
        public PlayerCharacterDao(AmazonDynamoDB dynamoDB, String tableName) {
            DynamoDB dynamoDBClient = new DynamoDB(dynamoDB);
            this.playerCharacterTable = dynamoDBClient.getTable(tableName);
        }

        public List<String> getCharactersByRole(String role) {
            List<String> characterNames = new ArrayList<>();

            ItemCollection<QueryOutcome> items = playerCharacterTable.query("role", role);
            Iterator<Item> iterator = items.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                String characterName = item.getString("name");
                characterNames.add(characterName);
            }

            return characterNames;
        }
    }

