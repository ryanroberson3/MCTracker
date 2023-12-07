package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

public class GetGameLogResult {
    private final GameLogModel gameLogModel;

    /**
     * GetGameLogResult Constructor.
     *
     * @param gameLogModel game log model to get a game log.
     */
    public GetGameLogResult(GameLogModel gameLogModel) {
        this.gameLogModel = gameLogModel;
    }

    /**
     * Get gameLogModel method.
     * @return game log model
     */
    public GameLogModel getGameLogModel() {
        return gameLogModel;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "GetGameLogResult{" +
                "gameLog=" + gameLogModel +
                '}';
    }

    /**
     * Static builder for class.
     * @return new builder
     */
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private GameLogModel gameLogModel;

        /**
         * Get gameLogModel build method.
         *
         * @param newGameLogModel for builder
         * @return game log model
         */
        public Builder withGameLog(GameLogModel newGameLogModel) {
            this.gameLogModel = newGameLogModel;
            return this;
        }

        /**
         * GetGameLogResult build method.
         * @return Get game log result
         */
        public GetGameLogResult build() {
            return new GetGameLogResult(gameLogModel);
        }
    }
}
