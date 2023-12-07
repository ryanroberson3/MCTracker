package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllGameLogsRequest.Builder.class)
public class GetAllGameLogsRequest {
    private final String email;

    /**
     * GetAllGameLogsRequest Constructor.
     *
     * @param email for user.
     */
    public GetAllGameLogsRequest(String email) {
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
        return "GetAllGameLogsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    /**
     * Static builder for class.
     * @return new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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
         * GetAllGameLogsRequest build method.
         * @return get all game logs request
         */
        public GetAllGameLogsRequest build() {
            return new GetAllGameLogsRequest(email);
        }
    }


}
