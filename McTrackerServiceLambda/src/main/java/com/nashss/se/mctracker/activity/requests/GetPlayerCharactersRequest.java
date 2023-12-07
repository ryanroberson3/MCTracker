package com.nashss.se.mctracker.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetPlayerCharactersRequest.Builder.class)
public class GetPlayerCharactersRequest {
    private final String role;

    /**
     * GetPlayerCharactersRequest Constructor.
     *
     * @param role for retrieving player characters.
     */
    public GetPlayerCharactersRequest(String role) {
        this.role = role;
    }

    /**
     * Get role method.
     * @return role
     */
    public String getRole() {
        return role;
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
        private String role;

        /**
         * Get role build method.
         *
         * @param newRole for builder
         * @return role
         */
        public Builder withRole(String newRole) {
            this.role = newRole;
            return this;
        }

        /**
         * GetPlayerCharactersRequest build method.
         * @return Get player characters request
         */
        public GetPlayerCharactersRequest build() {
            return new GetPlayerCharactersRequest(role);
        }
    }

}
