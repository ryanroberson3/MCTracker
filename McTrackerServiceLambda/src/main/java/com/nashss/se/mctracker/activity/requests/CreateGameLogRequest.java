package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.mctracker.dependency.CustomListDeserializer;

import java.util.List;

@JsonDeserialize(builder = CreateGameLogRequest.Builder.class)
public class CreateGameLogRequest {

    private final String email;
    private final String date;
    private final String outcomeWL;
    private final List<String> aspect;
    private final List<String> heroes;
    private final String villain;

    public CreateGameLogRequest(String email, String date, String outcomeWL,
                                List<String> aspect, List<String> heroes, String villain) {
        this.email = email;
        this.date = date;
        this.outcomeWL = outcomeWL;
        this.aspect = aspect;
        this.heroes = heroes;
        this.villain = villain;
    }

    public String getEmail() {
        return email;
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
        return "CreateGameLogRequest{" +
                "email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", outcomeWL='" + outcomeWL +
                ", aspect='" + aspect + '\'' +
                ", heroes='" + heroes + '\'' +
                ", villain='" + villain + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;
        private String date;
        private String outcomeWL;
        private List<String> aspect;
        private List<String> heroes;
        private String villain;

        public Builder withEmail(String email) {
            this.email = email;
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
        public CreateGameLogRequest build() {
            return new CreateGameLogRequest(email, date, outcomeWL, aspect, heroes, villain);
        }
    }
}
