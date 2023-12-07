package com.nashss.se.mctracker.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class GameLogModel {

    private String email;
    private String gameId;
    private LocalDate date;
    private String outcomeWL;
    private List<String> aspect;
    private List<String> heroes;
    private String villain;

    /**
     * GameLogModel Constructor.
     *
     * @param email for user.
     * @param gameId gameId for game log.
     * @param date the date played.
     * @param outcomeWL the outcome of the game.
     * @param aspect list of aspects.
     * @param heroes list of heroes.
     * @param villain villain faced during game.
     */
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

    /**
     * Get email method.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get GameId method.
     * @return gameId
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * Get date method.
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get outcomeWL method.
     * @return outcomeWL
     */
    public String getOutcomeWL() {
        return outcomeWL;
    }

    /**
     * Get aspect(s) method.
     * @return list of aspect(s)
     */
    public List<String> getAspect() {
        return aspect;
    }

    /**
     * Get hero(s) method.
     * @return list of hero(s)
     */
    public List<String> getHeroes() {
        return heroes;
    }

    /**
     * Get villain method.
     * @return villain
     */
    public String getVillain() {
        return villain;
    }

    /**
     * Equals method.
     * @return equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameLogModel that = (GameLogModel) o;
        return Objects.equals(email, that.email) && Objects.equals(gameId, that.gameId) &&
                Objects.equals(date, that.date) && Objects.equals(outcomeWL, that.outcomeWL) &&
                Objects.equals(aspect, that.aspect) && Objects.equals(heroes, that.heroes) &&
                Objects.equals(villain, that.villain);
    }

    /**
     * Hashcode method.
     * @return hash method
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, gameId, date, outcomeWL, aspect, heroes, villain);
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
        private LocalDate date;
        private String outcomeWL;
        private List<String> aspect;
        private List<String> heroes;
        private String villain;

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
         * Get date build method.
         *
         * @param newDate for builder
         * @return date
         */
        public Builder withDate(LocalDate newDate) {
            this.date = newDate;
            return this;
        }

        /**
         * Get outcomeWL build method.
         *
         * @param newOutcomeWL for builder
         * @return outcomeWL
         */
        public Builder withOutcomeWL(String newOutcomeWL) {
            this.outcomeWL = newOutcomeWL;
            return this;
        }

        /**
         * Get aspect(s) build method.
         *
         * @param newAspect list of aspect(s) for builder
         * @return list of aspect(s)
         */
        public Builder withAspect(List<String> newAspect) {
            this.aspect = newAspect;
            return this;
        }

        /**
         * Get hero(s) build method.
         *
         * @param newHeroes list of hero(s) for builder
         * @return list of hero(s)
         */
        public Builder withHeroes(List<String> newHeroes) {
            this.heroes = newHeroes;
            return this;
        }

        /**
         * Get villain build method.
         *
         * @param newVillain for builder
         * @return villain
         */
        public Builder withVillain(String newVillain) {
            this.villain = newVillain;
            return this;
        }

        /**
         * GameLogModel build method.
         * @return GameLogModel
         */
        public GameLogModel build() {
            return new GameLogModel(email, gameId, date, outcomeWL, aspect, heroes, villain);
        }
    }

}
