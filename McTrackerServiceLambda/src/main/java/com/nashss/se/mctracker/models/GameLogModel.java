package com.nashss.se.mctracker.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.nashss.se.mctracker.utils.CollectionUtils.copyToList;

public class GameLogModel {

    private String email;
    private String gameId;
    private LocalDate date;
    private String outcomeWL;
    private List<String> aspect;
    private List<String> heroes;
    private String villain;

    public GameLogModel(String email, String gameId, LocalDate date, String outcomeWL,
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

    public LocalDate getDate() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameLogModel that = (GameLogModel) o;
        return Objects.equals(email, that.email) && Objects.equals(gameId, that.gameId) && Objects.equals(date, that.date) && Objects.equals(outcomeWL, that.outcomeWL) && Objects.equals(aspect, that.aspect) && Objects.equals(heroes, that.heroes) && Objects.equals(villain, that.villain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, gameId, date, outcomeWL, aspect, heroes, villain);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String gameId;
        private LocalDate date;
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
        public Builder withDate(LocalDate date) {
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
        public GameLogModel build() {
            return new GameLogModel(email, gameId, date, outcomeWL, aspect, heroes, villain);
        }
    }

}
