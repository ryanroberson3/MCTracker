package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.DeleteGameLogRequest;
import com.nashss.se.mctracker.activity.results.DeleteGameLogResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteGameLogActivityTest {

    @InjectMocks
    private DeleteGameLogActivity activity;
    @Mock
    private GameLogDao gameLogDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void handleRequest_validGameLog_DeletesGameLog() {

        String email = "test@gmail.com";
        String gameId = "GAME123";
        String deleteSuccessful = String.format("GameLog, %s, successfully deleted", gameId);
        DeleteGameLogRequest request = new DeleteGameLogRequest(email, gameId);

        when(gameLogDao.deleteGameLog(email, gameId)).thenReturn(deleteSuccessful);

        DeleteGameLogResult result = activity.handleRequest(request);

        verify(gameLogDao).deleteGameLog(email, gameId);
        assertEquals(deleteSuccessful, result.getDeleteResult());
    }
}
