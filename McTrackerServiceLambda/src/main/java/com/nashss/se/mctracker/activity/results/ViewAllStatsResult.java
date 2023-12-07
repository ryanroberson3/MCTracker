package com.nashss.se.mctracker.activity.results;

import java.util.List;

public class ViewAllStatsResult {
    private final List<String> outcomes;
    private final double winStat;
    private final double lostBySchemeStat;
    private final double lostByDamageStat;

    /**
     * ViewAllStatsResult Constructor.
     *
     * @param outcomes list of all outcomes for a user.
     * @param winStat win percentage for user.
     * @param lostBySchemeStat lost by scheme percentage for user.
     * @param lostByDamageStat lost by damage percentage for user.
     */
    public ViewAllStatsResult(List<String> outcomes, double winStat, double lostBySchemeStat, double lostByDamageStat) {
        this.outcomes = outcomes;
        this.winStat = winStat;
        this.lostBySchemeStat = lostBySchemeStat;
        this.lostByDamageStat = lostByDamageStat;
    }

    /**
     * Get outcomes method.
     * @return list of outcomes
     */
    public List<String> getOutcomes() {
        return outcomes;
    }

    /**
     * Get winStat method.
     * @return winStat
     */
    public double getWinStat() {
        return winStat;
    }

    /**
     * Get lostBySchemeStat method.
     * @return lostBySchemeStat
     */
    public double getLostBySchemeStat() {
        return lostBySchemeStat;
    }

    /**
     * Get lostByDamageStat method.
     * @return lostByDamageStat
     */
    public double getLostByDamageStat() {
        return lostByDamageStat;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "ViewAllStatsResult{" +
                "outcomes=" + outcomes +
                ", winStat=" + winStat +
                ", lostBySchemeStat=" + lostBySchemeStat +
                ", lostByDamageStat=" + lostByDamageStat +
                '}';
    }

    /**
     * Static builder for class.
     * @return new builder
     */
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private List<String> outcomes;
        private double winStat;
        private double lostBySchemeStat;
        private double lostByDamageStat;

        /**
         * View outcomes build method.
         *
         * @param newOutcomes list of outcomes for builder
         * @return list of outcomes
         */
        public Builder withOutcomes(List<String> newOutcomes) {
            this.outcomes = newOutcomes;
            return this;
        }

        /**
         * View winStat build method.
         *
         * @param newWinStat for builder
         * @return winStat
         */
        public Builder withWinStat(double newWinStat) {
            this.winStat = newWinStat;
            return this;
        }

        /**
         * View lostBySchemeStat build method.
         *
         * @param newLostBySchemeStat for builder
         * @return lostBySchemeStat
         */
        public Builder withLostBySchemeStat(double newLostBySchemeStat) {
            this.lostBySchemeStat = newLostBySchemeStat;
            return this;
        }

        /**
         * View lostByDamageStat build method.
         *
         * @param newLostByDamageStat for builder
         * @return lostByDamageStat
         */
        public Builder withLostByDamageStat(double newLostByDamageStat) {
            this.lostByDamageStat = newLostByDamageStat;
            return this;
        }

        /**
         * ViewAllStatsResult build method.
         * @return View all stats result
         */
        public ViewAllStatsResult build() {
            return new ViewAllStatsResult(outcomes, winStat, lostBySchemeStat, lostByDamageStat);
        }
    }
}
