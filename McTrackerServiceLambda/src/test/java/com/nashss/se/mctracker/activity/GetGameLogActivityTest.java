package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetGameLogRequest;
import com.nashss.se.mctracker.activity.results.GetGameLogResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetGameLogActivityTest {

    @Mock
    private GameLogDao gameLogDao;
    private GetGameLogActivity getGameLogActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getGameLogActivity = new GetGameLogActivity(gameLogDao);
    }


    @Test
    public void handleRequest_withValidFields_getGameLogSuccess() {
        String email = "test@gmail.com";
        String gameId = "GAME45f";
        GameLog gameLog = new GameLog();
        gameLog.setGameId(gameId);
        gameLog.setEmail(email);
        gameLog.setDate(LocalDate.now());

        when(gameLogDao.getGameLogById(email, gameId)).thenReturn(gameLog);

        GetGameLogRequest gameLogRequest = GetGameLogRequest.builder()
                .withGameId(gameId)
                .withEmail(email)
                .build();
        GetGameLogResult result = getGameLogActivity.handleRequest(gameLogRequest);

        assertEquals(gameId, result.getGameLogModel().getGameId());
    }

    @Test
    public void handleRequest_gameLogNull_throwsGameNotFoundException() {

        when(gameLogDao.getGameLogById(null, null)).thenReturn(null);

        GetGameLogRequest gameLogRequest = GetGameLogRequest.builder()
                .withGameId(null)
                .withEmail(null)
                .build();

        assertThrows(GameNotFoundException.class,
                ()->{
                    getGameLogActivity.handleRequest(gameLogRequest);
                });
    }
}
