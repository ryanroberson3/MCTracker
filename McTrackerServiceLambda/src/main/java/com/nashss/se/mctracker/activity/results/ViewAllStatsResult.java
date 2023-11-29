package com.nashss.se.mctracker.activity.results;

import java.util.List;

public class ViewAllStatsResult {
    private final List<String> outcomes;

    public ViewAllStatsResult(List<String> outcomes) {
        this.outcomes = outcomes;
    }

    public List<String> getOutcomes() {
        return outcomes;
    }

    @Override
    public String toString() {
        return "ViewAllStatsResult{" +
                "outcomes=" + outcomes +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private List<String> outcomes;
        public Builder withOutcomes(List<String> outcomes) {
            this.outcomes = outcomes;
            return this;
        }
        public ViewAllStatsResult build() {
            return new ViewAllStatsResult(outcomes);
        }
    }
}
