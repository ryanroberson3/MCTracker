package com.nashss.se.mctracker.converters;

import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.models.GameLogModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {

    public GameLogModel toGameLogModel(GameLog gameLog) {
        return GameLogModel.builder()
                .withAspect(gameLog.getAspect())
                .withDate(gameLog.getDate())
                .withEmail(gameLog.getEmail())
                .withGameId(gameLog.getGameId())
                .withOutcomeWL(gameLog.getOutcomeWL())
                .withHeroes(gameLog.getHeroes())
                .withVillain(gameLog.getVillain())
                .build();
    }

    public List<GameLogModel> toGameLogModelList(List<GameLog> gameLogs) {
        List<GameLogModel> gameLogModels = new ArrayList<>();

        for (GameLog gameLog : gameLogs) {
            gameLogModels.add(toGameLogModel(gameLog));
        }

        return gameLogModels;
    }
}
