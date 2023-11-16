package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetPlayerCharactersRequest;
import com.nashss.se.mctracker.activity.results.GetPlayerCharactersResult;
import com.nashss.se.mctracker.dynamodb.PlayerCharacterDao;
import com.nashss.se.mctracker.dynamodb.models.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetPlayerCharactersActivityTest {
    @Mock
    private PlayerCharacterDao playerCharacterDao;

    private GetPlayerCharactersActivity getPlayerCharactersActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getPlayerCharactersActivity = new GetPlayerCharactersActivity(playerCharacterDao);
    }


    @Test
    void handleRequest_withHeroes_GrabsHeroes() {
        List<PlayerCharacter> playerCharacters = new ArrayList<>();
        List<String> players = new ArrayList<>();
        PlayerCharacter thor = new PlayerCharacter();
        thor.setName("thor");
        thor.setRole("Hero");
        PlayerCharacter ironMan = new PlayerCharacter();
        ironMan.setName("Iron Man");
        ironMan.setRole("Hero");

        playerCharacters.add(thor);
        playerCharacters.add(ironMan);

        for (PlayerCharacter character : playerCharacters) {
            String characterName = character.getName();
            players.add(characterName);
        }
        String role = "Hero";

        GetPlayerCharactersRequest request = GetPlayerCharactersRequest.builder()
                .withRole(role)
                .build();

        when(playerCharacterDao.getCharactersByRole(role)).thenReturn(players);

        GetPlayerCharactersResult result = getPlayerCharactersActivity.handleRequest(request);

        assertEquals(result.getPlayerCharacters().size(), players.size());

    }
}
