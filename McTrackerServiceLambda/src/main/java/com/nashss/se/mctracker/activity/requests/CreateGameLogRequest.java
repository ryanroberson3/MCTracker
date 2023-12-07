package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;


@JsonDeserialize(builder = CreateGameLogRequest.Builder.class)
public class CreateGameLogRequest {

    private final String email;
    private final String date;
    private final String outcomeWL;
    private final List<String> aspect;
    private final List<String> heroes;
    private final String villain;

    /**
     * CreateGameLogRequest Constructor.
     *
     * @param email for user.
     * @param date the date played.
     * @param outcomeWL the outcome of the game.
     * @param aspect list of aspects.
     * @param heroes list of heroes
     * @param villain villain faced during game
     */
    public CreateGameLogRequest(String email, String date, String outcomeWL,
                                List<String> aspect, List<String> heroes, String villain) {
        this.email = email;
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
     * Get date method.
     * @return date
     */
    public String getDate() {
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
     * ToString method for class.
     * @return to string
     */
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
        private String date;
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
         * Get date build method.
         *
         * @param newDate for builder
         * @return date
         */
        public Builder withDate(String newDate) {
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
         * @param newAspect for builder
         * @return list of aspect(s)
         */
        public Builder withAspect(List<String> newAspect) {
            this.aspect = newAspect;
            return this;
        }

        /**
         * Get hero(s) build method.
         *
         * @param newHeroes for builder
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
         * CreateGameLogRequest build method.
         * @return Create game log request builder
         */
        public CreateGameLogRequest build() {
            return new CreateGameLogRequest(email, date, outcomeWL, aspect, heroes, villain);
        }
    }
}
