package com.nashss.se.mctracker.activity.results;

import java.util.List;

public class GetPlayerCharactersResult {
    private final List<String> heroes;
    private final List<String> villains;

    public GetPlayerCharactersResult(List<String> heroes, List<String> villains) {
        this.heroes = heroes;
        this.villains = villains;
    }

    public List<String> getHeroes() {
        return heroes;
    }

    public List<String> getVillains() {
        return villains;
    }
}
