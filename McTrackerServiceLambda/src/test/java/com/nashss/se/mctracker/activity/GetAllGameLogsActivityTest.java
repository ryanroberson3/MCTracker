package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.GetAllGameLogsRequest;
import com.nashss.se.mctracker.activity.results.GetAllGameLogsResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAllGameLogsActivityTest {

    @Mock
    private GameLogDao gameLogDao;
    private GetAllGameLogsActivity getAllGameLogsActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        getAllGameLogsActivity = new GetAllGameLogsActivity(gameLogDao);
    }

    @Test
    public void handleRequest_withGameLogs_returnsAllGameLogs() {
        String email = "test@gmail.com";
        String game1Id = "GAME111";
        String game2Id = "GAME222";
        GameLog gameLog1 = new GameLog();
        GameLog gameLog2 = new GameLog();

        gameLog1.setEmail(email);
        gameLog1.setGameId(game1Id);
        gameLog1.setDate(LocalDate.now());

        gameLog2.setEmail(email);
        gameLog2.setGameId(game2Id);
        gameLog2.setDate(LocalDate.now().plusDays(1));

        List<GameLog> gameLogs = new ArrayList<>();
        gameLogs.add(gameLog1);
        gameLogs.add(gameLog2);

        when(gameLogDao.getAllGameLogsByEmail(email)).thenReturn(gameLogs);

        GetAllGameLogsRequest getAllGameLogsRequest = GetAllGameLogsRequest.builder()
                .withEmail(email)
                .build();
        GetAllGameLogsResult result = getAllGameLogsActivity.handleRequest(getAllGameLogsRequest);

        assertEquals(gameLogs.size(), result.getGameLogList().size());
    }
}
