package com.nashss.se.mctracker.activity.results;

import java.util.List;

public class GetPlayerCharactersResult {
    private final List<String> playerCharacters;

    public GetPlayerCharactersResult(List<String> playerCharacters) {
        this.playerCharacters = playerCharacters;
    }

    public List<String> getPlayerCharacters() {
        return playerCharacters;
    }
}
