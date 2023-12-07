package com.nashss.se.mctracker.activity.results;

import java.util.ArrayList;
import java.util.List;

public class GetPlayerCharactersResult {
    private final List<String> playerCharacters;

    /**
     * GetPlayerCharactersResult Constructor.
     *
     * @param playerCharacters list of player characters from table.
     */
    public GetPlayerCharactersResult(List<String> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    /**
     * Get playerCharacters method.
     * @return list of player characters
     */
    public List<String> getPlayerCharacters() {
        return playerCharacters;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "GetPlayerCharactersResult{" +
                "playerCharacters=" + playerCharacters +
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
        private List<String> playerCharacters;

        /**
         * Get playerCharacters build method.
         *
         * @param newPlayerCharacters list of player characters for builder
         * @return list of player characters
         */
        public Builder withPlayerCharacterList(List<String> newPlayerCharacters) {
            this.playerCharacters = new ArrayList<>(newPlayerCharacters);
            return this;
        }

        /**
         * GetPlayerCharactersResult build method.
         * @return Get player characters result
         */
        public GetPlayerCharactersResult build() {
            return new GetPlayerCharactersResult(playerCharacters);
        }
    }
}
