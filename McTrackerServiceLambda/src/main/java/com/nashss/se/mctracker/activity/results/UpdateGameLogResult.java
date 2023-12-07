package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

public class UpdateGameLogResult {
    private final GameLogModel gameLog;

    /**
     * UpdateGameLogResult Constructor.
     *
     * @param gameLog game log model to update a game log.
     */
    public UpdateGameLogResult(GameLogModel gameLog) {
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
        return "UpdateGameLogResult{" +
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
         * Update gameLog build method.
         *
         * @param newGameLog for builder
         * @return gameLog
         */
        public Builder withGameLog(GameLogModel newGameLog) {
            this.gameLog = newGameLog;
            return this;
        }

        /**
         * UpdateGameLogResult build method.
         * @return Update game log result
         */
        public UpdateGameLogResult build() {
            return new UpdateGameLogResult(gameLog);
        }
    }
}
