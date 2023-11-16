package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllGameLogsRequest.Builder.class)
public class GetAllGameLogsRequest {
    private final String email;

    public GetAllGameLogsRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "GetAllGameLogsRequest{" +
                "email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String email;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public GetAllGameLogsRequest build() {
            return new GetAllGameLogsRequest(email);
        }
    }


}
