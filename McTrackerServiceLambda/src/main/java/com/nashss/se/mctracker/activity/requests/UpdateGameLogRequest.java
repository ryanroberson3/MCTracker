package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = UpdateGameLogRequest.Builder.class)
public class UpdateGameLogRequest {
    private final String email;
    private final String gameId;
    private final String date;
    private final String outcomeWL;
    private final List<String> aspect;
    private final List<String> heroes;
    private final String villain;

    public UpdateGameLogRequest(String email, String gameId, String date, String outcomeWL,
                                List<String> aspect, List<String> heroes, String villain) {
        this.email = email;
        this.gameId = gameId;
        this.date = date;
        this.outcomeWL = outcomeWL;
        this.aspect = aspect;
        this.heroes = heroes;
        this.villain = villain;
    }

    public String getEmail() {
        return email;
    }

    public String getGameId() {
        return gameId;
    }

    public String getDate() {
        return date;
    }

    public String getOutcomeWL() {
        return outcomeWL;
    }

    public List<String> getAspect() {
        return aspect;
    }

    public List<String> getHeroes() {
        return heroes;
    }

    public String getVillain() {
        return villain;
    }

    @Override
    public String toString() {
        return "UpdateGameLogRequest{" +
                "email='" + email + '\'' +
                ", gameId='" + gameId + '\'' +
                ", date='" + date + '\'' +
                ", outcomeWL='" + outcomeWL + '\'' +
                ", aspect=" + aspect +
                ", heroes=" + heroes +
                ", villain='" + villain + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String gameId;
        private String date;
        private String outcomeWL;
        private List<String> aspect;
        private List<String> heroes;
        private String villain;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }
        public Builder withOutcomeWL(String outcomeWL) {
            this.outcomeWL = outcomeWL;
            return this;
        }
        public Builder withAspect(List<String> aspect) {
            this.aspect = aspect;
            return this;
        }
        public Builder withHeroes(List<String> heroes) {
            this.heroes = heroes;
            return this;
        }
        public Builder withVillain(String villain) {
            this.villain = villain;
            return this;
        }

        public UpdateGameLogRequest build() {
            return new UpdateGameLogRequest(email, gameId, date, outcomeWL, aspect, heroes, villain);
        }
    }
}
