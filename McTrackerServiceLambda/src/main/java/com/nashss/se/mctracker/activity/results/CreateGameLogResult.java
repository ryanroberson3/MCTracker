package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

public class CreateGameLogResult {
    private final GameLogModel gameLog;

    private CreateGameLogResult(GameLogModel gameLog) {
        this.gameLog = gameLog;
    }
    public GameLogModel getGameLog() {
        return gameLog;
    }
    @Override
    public String toString() {
        return "CreateGameLogResult{" +
                "gameLog=" + gameLog +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private GameLogModel gameLog;
        public Builder withGameLog(GameLogModel gameLog) {
            this.gameLog = gameLog;
            return this;
        }
        public CreateGameLogResult build() {
            return new CreateGameLogResult(gameLog);
        }
    }
}
