package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.CreateGameLogRequest;
import com.nashss.se.mctracker.activity.results.CreateGameLogResult;
import com.nashss.se.mctracker.converters.LocalDateConverter;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateGameLogActivityTest {

    @Mock
    private GameLogDao gameLogDao;
    private CreateGameLogActivity createGameLogActivity;

    @BeforeEach
    void setUp() {
        openMocks(this);
        createGameLogActivity = new CreateGameLogActivity(gameLogDao);
    }

    @Test
    public void handleRequest_withValidFields_createsAndSavesGameLog() {
        LocalDateConverter converter = new LocalDateConverter();

        LocalDate date = LocalDate.now();

        List<String> heroList = new ArrayList<>();
        heroList.add("Iron Man");

        String villain = "Ultron";

        String outcome = "won";

        List<String> aspect = new ArrayList<>();
                aspect.add("Justice");

        String email = "test@gmail.com";

        CreateGameLogRequest request = CreateGameLogRequest.builder()
                .withDate(converter.convert(date))
                .withAspect(aspect)
                .withEmail(email)
                .withHeroes(heroList)
                .withOutcomeWL(outcome)
                .withVillain(villain)
                .build();

        CreateGameLogResult result = createGameLogActivity.handleRequest(request);

        verify(gameLogDao).saveGameLog(any(GameLog.class));

        assertNotNull(result.getGameLog());
        assertEquals(aspect, result.getGameLog().getAspect());

    }
}
