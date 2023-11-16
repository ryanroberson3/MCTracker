package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

import java.util.List;

public class GetAllGameLogsResult {
    private final List<GameLogModel> gameLogList;

    public GetAllGameLogsResult(List<GameLogModel> gameLogList) {
        this.gameLogList = gameLogList;
    }
    public List<GameLogModel> getGameLogList() {
        return gameLogList;
    }
    @Override
    public String toString() {
        return "GetAllGameLogsResult{" +
                "gameLogList=" + gameLogList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<GameLogModel> gameLogList;

        public Builder withGameLogList(List<GameLogModel> gameLogList) {
            this.gameLogList = gameLogList;
            return this;
        }

        public GetAllGameLogsResult build() {
            return new GetAllGameLogsResult(gameLogList);
        }
    }
}
