package com.nashss.se.mctracker.activity.results;

public class DeleteGameLogResult {
    private final String deleteResult;

    /**
     * DeleteGameLogResult Constructor.
     *
     * @param deleteResult string to inform that game log was deleted.
     */
    public DeleteGameLogResult(String deleteResult) {
        this.deleteResult = deleteResult;
    }

    /**
     * Delete method for class.
     * @return delete result
     */
    public String getDeleteResult() {
        return deleteResult;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "DeleteGameLogResult{" +
                "deleteResult='" + deleteResult + '\'' +
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
        private String deleteResult;

        /**
         * Delete build method.
         *
         * @param newDeleteResult for builder
         * @return deleteResult
         */
        public Builder withDeleteResult(String newDeleteResult) {
            this.deleteResult = newDeleteResult;
            return this;
        }

        /**
         * DeleteGameLogResult build method.
         * @return Delete game log result
         */
        public DeleteGameLogResult build() {
            return new DeleteGameLogResult(deleteResult);
        }
    }
}
