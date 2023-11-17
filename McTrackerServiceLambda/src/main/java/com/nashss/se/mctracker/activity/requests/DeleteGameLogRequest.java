package com.nashss.se.mctracker.activity.requests;

public class DeleteGameLogRequest {
    private final String email;
    private final String gameId;

    public DeleteGameLogRequest(String email, String gameId) {
        this.email = email;
        this.gameId = gameId;
    }
    public String getEmail() {
        return email;
    }

    public String getGameId() {
        return gameId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String gameId;
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public DeleteGameLogRequest build() {
            return new DeleteGameLogRequest(email, gameId);
        }
    }
}
