package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetPlayerCharactersRequest;
import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;
import com.nashss.se.mctracker.dynamodb.PlayerCharacterDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;



public class GetPlayerCharactersActivity {

    private final Logger log = LogManager.getLogger();
    private final PlayerCharacterDao playerCharacterDao;

    /**
     * Instantiates a new GetPlayerCharactersActivity object.
     *
     * @param playerCharacterDao to access the Player Character Dao.
     */
    @Inject
    public GetPlayerCharactersActivity(PlayerCharacterDao playerCharacterDao) {
        this.playerCharacterDao = playerCharacterDao;
    }

    /**
     * This method handles the incoming request for getting all the player characters and populating the dropdown menus.
     * <p>
     * It then returns all the player characters for populating thw dropdowns on the site.
     *
     * @param getPlayerCharactersRequest request object containing the role to retrieve
     *                                   all the player characters with that specific role.
     * @return Get player character request
     */
    public GetPlayerCharactersResult handleRequest(final GetPlayerCharactersRequest getPlayerCharactersRequest) {
        log.info("Received GetPlayerCharactersRequest {}", getPlayerCharactersRequest);
        String role = getPlayerCharactersRequest.getRole();
        List<String> playerCharacters = playerCharacterDao.getCharactersByRole(role);
        GetPlayerCharactersResult result = GetPlayerCharactersResult.builder()
                .withPlayerCharacterList(playerCharacters)
                .build();
        return result;
    }
}
