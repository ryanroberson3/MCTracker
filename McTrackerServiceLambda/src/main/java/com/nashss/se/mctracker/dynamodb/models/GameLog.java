package com.nashss.se.mctracker.dynamodb.models;

import com.nashss.se.mctracker.converters.LocalDateConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@DynamoDBTable(tableName = "game_logs")
public class GameLog {

    private String email;
    private String gameId;
    private LocalDate date;
    private String outcomeWL;
    private List<String> aspect;
    private List<String> heroes;
    private String villain;

    @DynamoDBHashKey(attributeName = "email")
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {"TotalWLIndex"}, attributeName = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @DynamoDBRangeKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    @DynamoDBAttribute(attributeName = "date")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @DynamoDBAttribute(attributeName = "outcomeWL")
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "TotalWLIndex", attributeName = "outcomeWL")
    public String getOutcomeWL() {
        return outcomeWL;
    }

    public void setOutcomeWL(String outcomeWL) {
        this.outcomeWL = outcomeWL;
    }
    @DynamoDBAttribute(attributeName = "aspect")
    public List<String> getAspect() {
        return aspect;
    }

    public void setAspect(List<String> aspect) {
        this.aspect = aspect;
    }
    @DynamoDBAttribute(attributeName = "heroes")
    public List<String> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<String> heroes) {
        this.heroes = heroes;
    }
    @DynamoDBAttribute(attributeName = "villain")
    public String getVillain() {
        return villain;
    }

    public void setVillain(String villain) {
        this.villain = villain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameLog gameLog = (GameLog) o;
        return Objects.equals(email, gameLog.email) && Objects.equals(gameId, gameLog.gameId) &&
                Objects.equals(date, gameLog.date) && Objects.equals(outcomeWL, gameLog.outcomeWL) &&
                Objects.equals(aspect, gameLog.aspect) && Objects.equals(heroes, gameLog.heroes) &&
                Objects.equals(villain, gameLog.villain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, gameId, date, outcomeWL, aspect, heroes, villain);
    }
}
