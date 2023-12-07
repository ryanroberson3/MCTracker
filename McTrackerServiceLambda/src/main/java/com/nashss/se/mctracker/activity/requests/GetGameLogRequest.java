package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetGameLogRequest.Builder.class)
public class GetGameLogRequest {
    private final String email;
    private final String gameId;

    /**
     * GetGameLogRequest Constructor.
     *
     * @param email for user.
     * @param gameId the gameId for the log.
     */
    public GetGameLogRequest(String email, String gameId) {
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
    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String gameId;

        /**
         * Get email build method.
         *
         * @param newEmail for builder
         * @return email
         */
        public Builder withEmail(String newEmail) {
            this.email = newEmail;
            return this;
        }

        /**
         * Get gameId build method.
         *
         * @param newGameId for builder
         * @return gameId
         */
        public Builder withGameId(String newGameId) {
            this.gameId = newGameId;
            return this;
        }

        /**
         * GetGameLogRequest build method.
         * @return get game log request
         */
        public GetGameLogRequest build() {
            return new GetGameLogRequest(email, gameId);
        }
    }
}
