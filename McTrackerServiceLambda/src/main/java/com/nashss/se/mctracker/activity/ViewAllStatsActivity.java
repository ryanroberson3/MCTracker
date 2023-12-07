package com.nashss.se.mctracker.activity;

import com.nashss.se.mctracker.activity.requests.ViewAllStatsRequest;
import com.nashss.se.mctracker.activity.results.ViewAllStatsResult;
import com.nashss.se.mctracker.dynamodb.GameLogDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class ViewAllStatsActivity {
    private final Logger log = LogManager.getLogger();
    private final GameLogDao gameLogDao;

    /**
     * Instantiates a new ViewAllStatsActivity object.
     *
     * @param gameLogDao to access the Game Log Dao.
     */
    @Inject
    public ViewAllStatsActivity(GameLogDao gameLogDao) {
        this.gameLogDao = gameLogDao;
    }

    /**
     * This method handles the incoming request by getting all the w/l stats for a certain user.
     * <p>
     * It then returns all the state from a certain user to populate a pie chart.
     *
     * @param viewAllStatsRequest request object containing the email to retrieve all the stats.
     * @return View all stats result
     */
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
