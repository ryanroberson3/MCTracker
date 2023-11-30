package com.nashss.se.mctracker.activity.results;

import java.util.List;

public class ViewAllStatsResult {
    private final List<String> outcomes;
    private final double winStat;
    private final double lostBySchemeStat;
    private final double lostByDamageStat;

    public ViewAllStatsResult(List<String> outcomes, double winStat, double lostBySchemeStat, double lostByDamageStat) {
        this.outcomes = outcomes;
        this.winStat = winStat;
        this.lostBySchemeStat = lostBySchemeStat;
        this.lostByDamageStat = lostByDamageStat;
    }

    public List<String> getOutcomes() {
        return outcomes;
    }

    public double getWinStat() {
        return winStat;
    }

    public double getLostBySchemeStat() {
        return lostBySchemeStat;
    }

    public double getLostByDamageStat() {
        return lostByDamageStat;
    }
    @Override
    public String toString() {
        return "ViewAllStatsResult{" +
                "outcomes=" + outcomes +
                ", winStat=" + winStat +
                ", lostBySchemeStat=" + lostBySchemeStat +
                ", lostByDamageStat=" + lostByDamageStat +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private List<String> outcomes;
        private double winStat;
        private double lostBySchemeStat;
        private double lostByDamageStat;
        public Builder withOutcomes(List<String> outcomes) {
            this.outcomes = outcomes;
            return this;
        }
        public Builder withWinStat(double winStat) {
            this.winStat = winStat;
            return this;
        }
        public Builder withLostBySchemeStat(double lostBySchemeStat) {
            this.lostBySchemeStat = lostBySchemeStat;
            return this;
        }
        public Builder withLostByDamageStat(double lostByDamageStat) {
            this.lostByDamageStat = lostByDamageStat;
            return this;
        }

        public ViewAllStatsResult build() {
            return new ViewAllStatsResult(outcomes, winStat, lostBySchemeStat, lostByDamageStat);
        }
    }
}
