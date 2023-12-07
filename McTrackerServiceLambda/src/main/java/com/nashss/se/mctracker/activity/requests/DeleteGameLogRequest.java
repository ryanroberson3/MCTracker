package com.nashss.se.mctracker.activity.requests;

public class DeleteGameLogRequest {
    private final String email;
    private final String gameId;

    /**
     * DeleteGameLogRequest Constructor.
     *
     * @param email for user.
     * @param gameId the gameId for the log.
     */
    public DeleteGameLogRequest(String email, String gameId) {
        this.email = email;
        this.gameId = gameId;
    }

    /**
     * Get email method.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get gameId method.
     * @return gameId
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Static builder for class.
     * @return new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String gameId;

        /**
         * Delete email build method.
         *
         * @param newEmail for builder
         * @return email
         */
        public Builder withEmail(String newEmail) {
            this.email = newEmail;
            return this;
        }

        /**
         * Delete gameId build method.
         *
         * @param newGameId for builder
         * @return gameId
         */
        public Builder withGameId(String newGameId) {
            this.gameId = newGameId;
            return this;
        }

        /**
         * DeleteGameLogRequest build method.
         * @return Delete game log request builder
         */
        public DeleteGameLogRequest build() {
            return new DeleteGameLogRequest(email, gameId);
        }
    }
}
