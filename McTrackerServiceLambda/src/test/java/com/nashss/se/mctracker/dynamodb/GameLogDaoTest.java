package com.nashss.se.mctracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import com.nashss.se.mctracker.metrics.MetricsConstants;
import com.nashss.se.mctracker.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameLogDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    private GameLogDao gameLogDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        this.gameLogDao = new GameLogDao(dynamoDBMapper, metricsPublisher);
    }


    @Test
    public void getGameLogById_withValidInputs_returnsGameLog() {
        String email = "test@gmail.com";
        String gameId = "GAME1g4";

        when(dynamoDBMapper.load(GameLog.class, email, gameId)).thenReturn(new GameLog());

        GameLog gameLog = gameLogDao.getGameLogById(email, gameId);

        assertNotNull(gameLog);
        verify(dynamoDBMapper).load(GameLog.class, email, gameId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void getGameLogById_gameLogNotFound_throwsException() {
        String email = "fake";
        String gameId = "fake";

        when(dynamoDBMapper.load(GameLog.class, email, gameId)).thenReturn(null);

        assertThrows(GameNotFoundException.class, () -> gameLogDao.getGameLogById(email, gameId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAMELOG_GAMELOGNOTFOUND_COUNT), anyDouble());
    }
}
