package com.nashss.se.mctracker.activity.results;

public class DeleteGameLogResult {
    private final String deleteResult;

    public DeleteGameLogResult(String deleteResult) {
        this.deleteResult = deleteResult;
    }

    public String getDeleteResult() {
        return deleteResult;
    }
    @Override
    public String toString() {
        return "DeleteGameLogResult{" +
                "deleteResult='" + deleteResult + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String deleteResult;
        public Builder withDeleteResult(String deleteResult) {
            this.deleteResult = deleteResult;
            return this;
        }
        public DeleteGameLogResult build() {
            return new DeleteGameLogResult(deleteResult);
        }
    }
}
