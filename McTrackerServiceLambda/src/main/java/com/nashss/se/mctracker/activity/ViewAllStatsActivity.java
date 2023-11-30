package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.ViewAllStatsRequest;
import com.nashss.se.mctracker.activity.results.ViewAllStatsResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class ViewAllStatsActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;
    @Inject
    public ViewAllStatsActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }
    public ViewAllStatsResult handleRequest(final ViewAllStatsRequest viewAllStatsRequest) {
        log.info("Received ViewAllStatsRequest {}", viewAllStatsRequest);
        String email = viewAllStatsRequest.getEmail();

        List<String> outcomes = gameLogDao.viewAllStats(email);

        int winCounter = Collections.frequency(outcomes, "Win");
        int lostBySchemeCounter = Collections.frequency(outcomes, "Lost by scheme");
        int lostByDamageCounter = Collections.frequency(outcomes, "Lost by damage");

        double winPercentage = ((double) winCounter / outcomes.size()) * 100;
        double lostBySchemePercentage = ((double) lostBySchemeCounter / outcomes.size()) * 100;
        double lostByDamagePercentage = ((double) lostByDamageCounter / outcomes.size()) * 100;

        return ViewAllStatsResult.builder()
                .withOutcomes(outcomes)
                .withWinStat(winPercentage)
                .withLostBySchemeStat(lostBySchemePercentage)
                .withLostByDamageStat(lostByDamagePercentage)
                .build();
    }

}
