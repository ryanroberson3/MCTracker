package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetPlayerCharactersRequest.Builder.class)
public class GetPlayerCharactersRequest {
    private final String role;

    public GetPlayerCharactersRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String role;

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public GetPlayerCharactersRequest build() {
            return new GetPlayerCharactersRequest(role);
        }
    }

}
