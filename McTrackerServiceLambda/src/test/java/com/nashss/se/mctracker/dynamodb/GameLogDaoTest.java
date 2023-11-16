package com.nashss.se.mctracker.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.mctracker.dynamodb.models.GameLog;
import com.nashss.se.mctracker.exceptions.GameNotFoundException;
import com.nashss.se.mctracker.metrics.MetricsConstants;
import com.nashss.se.mctracker.metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameLogDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private MetricsPublisher metricsPublisher;
    @Mock
    private PaginatedQueryList<GameLog> paginatedQueryList;
    @Captor
    private ArgumentCaptor<DynamoDBQueryExpression<GameLog>> queryCaptor;
    private GameLogDao gameLogDao;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        this.gameLogDao = new GameLogDao(dynamoDBMapper, metricsPublisher);

        when(paginatedQueryList.toArray()).thenReturn(new Object[0]);
    }


    @Test
    public void saveGameLog_callsDyanamoDbWithgameLog() {

        GameLog gameLog = new GameLog();

        GameLog result = gameLogDao.saveGameLog(gameLog);

        verify(dynamoDBMapper).save(gameLog);
        assertEquals(gameLog, result);
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

    @Test
    public void getAllGameLogsByEmail_withEmail_returnsGameLogs() {
        String email = "test@gmail.com";

        when(dynamoDBMapper.query(eq(GameLog.class), any(DynamoDBQueryExpression.class))).thenReturn(paginatedQueryList);

        List<GameLog> result = gameLogDao.getAllGameLogsByEmail(email);

        assertNotNull(result);
        assertEquals(paginatedQueryList, result);
        verify(dynamoDBMapper, times(1)).query(eq(GameLog.class), queryCaptor.capture());

    }
}
