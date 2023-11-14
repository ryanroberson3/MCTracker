package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.dynamodb.PlayerCharacterDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

    }
}
