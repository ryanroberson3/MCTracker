package com.nashss.se.mctracker.activity.results;

import com.nashss.se.mctracker.models.GameLogModel;

import java.util.List;

public class GetAllGameLogsResult {
    private final List<GameLogModel> gameLogList;

    /**
     * GetAllGameLogsResult Constructor.
     *
     * @param gameLogList list of game log model to get all game logs.
     */
    public GetAllGameLogsResult(List<GameLogModel> gameLogList) {
        this.gameLogList = gameLogList;
    }

    /**
     * Get gameLogList method.
     * @return list of game logs
     */
    public List<GameLogModel> getGameLogList() {
        return gameLogList;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "GetAllGameLogsResult{" +
                "gameLogList=" + gameLogList +
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
        private List<GameLogModel> gameLogList;

        /**
         * Get gameLogList build method.
         *
         * @param newGameLogList list of gameLogs for builder
         * @return list of game logs
         */
        public Builder withGameLogList(List<GameLogModel> newGameLogList) {
            this.gameLogList = newGameLogList;
            return this;
        }

        /**
         * GetAllGameLogsResult build method.
         * @return Get all game logs result
         */
        public GetAllGameLogsResult build() {
            return new GetAllGameLogsResult(gameLogList);
        }
    }
}
