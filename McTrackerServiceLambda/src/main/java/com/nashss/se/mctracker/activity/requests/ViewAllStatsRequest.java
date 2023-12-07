package com.nashss.se.mctracker.activity.requests;

public class ViewAllStatsRequest {
    private final String email;

    /**
     * ViewAllStatsRequest Constructor.
     *
     * @param email for user.
     */
    public ViewAllStatsRequest(String email) {
        this.email = email;
    }

    /**
     * Get email method.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * ToString method for class.
     * @return to string
     */
    @Override
    public String toString() {
        return "ViewAllStatsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    /**
     * Static builder for class.
     * @return new Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;

        /**
         * Get email build method.
         *
         * @param newEmail for builder
         * @return email
         */
        public Builder withEmail(String newEmail) {
            this.email = newEmail;
            return this;
        }

        /**
         * ViewAllStatsRequest build method.
         * @return View all stats request
         */
        public ViewAllStatsRequest build() {
            return new ViewAllStatsRequest(email);
        }
    }
}
