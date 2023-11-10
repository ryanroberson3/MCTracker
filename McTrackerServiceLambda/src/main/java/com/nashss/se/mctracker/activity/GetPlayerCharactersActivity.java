package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;
import com.nashss.se.mctracker.dynamodb.PlayerCharacterDao;

import javax.inject.Inject;
import java.util.List;


public class GetPlayerCharactersActivity {
    private final PlayerCharacterDao playerCharacterDao;
    @Inject
    public GetPlayerCharactersActivity(PlayerCharacterDao playerCharacterDao) {
        this.playerCharacterDao = playerCharacterDao;
    }

    public GetPlayerCharactersResult processData(String role) {
        List<String> heroes = playerCharacterDao.getCharactersByRole("Hero");
        List<String> villains = playerCharacterDao.getCharactersByRole("Villain");
        return new GetPlayerCharactersResult(heroes, villains);
    }
}
