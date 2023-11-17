package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.UpdateGameLogRequest;
import com.nashss.se.mctracker.activity.results.UpdateGameLogResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateGameLogActivityTest {

    @Mock
    private GameLogDao gameLogDao;
    @Mock
    private MetricsPublisher metricsPublisher;
    private UpdateGameLogActivity updateGameLogActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateGameLogActivity = new UpdateGameLogActivity(gameLogDao, metricsPublisher);
    }

    @Test
    public void handleRequest_goodInputs_updatesGameLog() {
        String email = "test@gmail.com";
        String gameId = "GAME123";

        LocalDate expectedDate = LocalDate.now();
        String expectedOutcomeWL = "Win";
        List<String> expectedAspect = List.of("Justice, Aggression");
        List<String> expectedHeroes = List.of("Iron Man, Captain America");
        String expectedVillain = "Villain";

        UpdateGameLogRequest request = UpdateGameLogRequest.builder()
                .withEmail(email)
                .withGameId(gameId)
                .withDate(String.valueOf(expectedDate))
                .withOutcomeWL(expectedOutcomeWL)
                .withAspect(expectedAspect)
                .withHeroes(expectedHeroes)
                .withVillain(expectedVillain)
                .build();

        GameLog startingGameLog = new GameLog();
        startingGameLog.setEmail(email);
        startingGameLog.setGameId(gameId);
        startingGameLog.setDate(LocalDate.now().plusDays(2));
        startingGameLog.setOutcomeWL("Lost");
        startingGameLog.setAspect(List.of("Basic, Leadership"));
        startingGameLog.setHeroes(List.of("Iron Man, Captain Marvel"));
        startingGameLog.setVillain("Ultron");

        when(gameLogDao.getGameLogById(email, gameId)).thenReturn(startingGameLog);
        when(gameLogDao.saveGameLog(startingGameLog)).thenReturn(startingGameLog);

        UpdateGameLogResult result = updateGameLogActivity.handleRequest(request);

        assertEquals(expectedDate, result.getGameLog().getDate());
        assertEquals(expectedOutcomeWL, result.getGameLog().getOutcomeWL());
        assertEquals(expectedAspect, result.getGameLog().getAspect());
        assertEquals(expectedHeroes, result.getGameLog().getHeroes());
        assertEquals(expectedVillain, result.getGameLog().getVillain());

    }
}
