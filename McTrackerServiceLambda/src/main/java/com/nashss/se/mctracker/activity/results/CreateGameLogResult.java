package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

public class CreateGameLogResult {
    private final GameLogModel gameLog;

    /**
     * CreateGameLogResult Constructor.
     *
     * @param gameLog game log model to create a game log.
     */
    private CreateGameLogResult(GameLogModel gameLog) {
        this.gameLog = gameLog;
    }

    /**
     * Get gameLog method.
     * @return gameLog
     */
    public GameLogModel getGameLog() {
        return gameLog;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "CreateGameLogResult{" +
                "gameLog=" + gameLog +
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
        private GameLogModel gameLog;

        /**
         * Get gameLog build method.
         *
         * @param newGameLog for builder
         * @return gameLog
         */
        public Builder withGameLog(GameLogModel newGameLog) {
            this.gameLog = newGameLog;
            return this;
        }

        /**
         * CreateGameLogResult build method.
         * @return Create game log request
         */
        public CreateGameLogResult build() {
            return new CreateGameLogResult(gameLog);
        }
    }
}
