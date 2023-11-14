package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

public class GetGameLogResult {
    private final GameLogModel gameLogModel;

    public GetGameLogResult(GameLogModel gameLogModel) {
        this.gameLogModel = gameLogModel;
    }

    public GameLogModel getGameLogModel() {
        return gameLogModel;
    }

    @Override
    public String toString() {
        return "GetGameLogResult{" +
                "gameLog=" + gameLogModel +
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        private GameLogModel gameLogModel;
        public Builder withGameLog(GameLogModel gameLogModel){
            this.gameLogModel = gameLogModel;
            return this;
        }
        public GetGameLogResult build(){
            return new GetGameLogResult(gameLogModel);
        }
    }
}
