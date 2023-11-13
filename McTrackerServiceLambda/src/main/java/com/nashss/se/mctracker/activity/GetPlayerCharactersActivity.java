package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetPlayerCharactersRequest;
import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;
import com.nashss.se.mctracker.dynamodb.PlayerCharacterDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;


public class GetPlayerCharactersActivity {

    private final Logger log = LogManager.getLogger();
    private final PlayerCharacterDao playerCharacterDao;
    @Inject
    public GetPlayerCharactersActivity(PlayerCharacterDao playerCharacterDao) {
        this.playerCharacterDao = playerCharacterDao;
    }

    public GetPlayerCharactersResult handleRequest(final GetPlayerCharactersRequest getPlayerCharactersRequest) {
        log.info("Received GetPlayerCharactersRequest {}", getPlayerCharactersRequest);

        String role = getPlayerCharactersRequest.getRole();
        List<String> playerCharacters = playerCharacterDao.getCharactersByRole(role);
        return new GetPlayerCharactersResult(playerCharacters);
    }
}
