package com.nashss.se.mctracker.activity.requests;

public class ViewAllStatsRequest {
    private final String email;

    public ViewAllStatsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ViewAllStatsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder();}

    public static class Builder {
        private String email;
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public ViewAllStatsRequest build() {
            return new ViewAllStatsRequest(email);
        }
    }
}
