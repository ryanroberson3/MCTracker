package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetGameLogRequest.Builder.class)
public class GetGameLogRequest {
    private final String email;
    private final String gameId;

    public GetGameLogRequest(String email, String gameId) {
        this.email = email;
        this.gameId = gameId;
    }

    public String getEmail() {
        return email;
    }

    public String getGameId() {
        return gameId;
    }

    public static Builder builder(){
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{
        private String email;
        private String gameId;

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Builder withGameId(String gameId){
            this.gameId = gameId;
            return this;
        }
        public GetGameLogRequest build() {
            return new GetGameLogRequest(email, gameId);
        }
    }
}
