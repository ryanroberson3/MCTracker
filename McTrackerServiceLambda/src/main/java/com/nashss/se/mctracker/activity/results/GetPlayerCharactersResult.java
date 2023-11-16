package com.nashss.se.mctracker.activity.results;


import java.util.ArrayList;
import java.util.List;

public class GetPlayerCharactersResult {
    private final List<String> playerCharacters;

    public GetPlayerCharactersResult(List<String> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public List<String> getPlayerCharacters() {
        return playerCharacters;
    }

    @Override
    public String toString() {
        return "GetPlayerCharactersResult{" +
                "playerCharacters=" + playerCharacters +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> playerCharacters;

        public Builder withPlayerCharacterList(List<String> playerCharacters) {
            this.playerCharacters = new ArrayList<>(playerCharacters);
            return this;
        }

        public GetPlayerCharactersResult build() {
            return new GetPlayerCharactersResult(playerCharacters);
        }
    }
}
