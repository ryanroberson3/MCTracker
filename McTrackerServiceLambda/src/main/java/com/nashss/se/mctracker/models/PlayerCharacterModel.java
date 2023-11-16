//package com.nashss.se.mctracker.models;
//
//import java.util.Objects;
//
//public class PlayerCharacterModel {
//    private String name;
//    private String role;
//
//    public PlayerCharacterModel(String name, String role) {
//        this.name = name;
//        this.role = role;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PlayerCharacterModel that = (PlayerCharacterModel) o;
//        return Objects.equals(name, that.name) && Objects.equals(role, that.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, role);
//    }
//
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder {
//        private String name;
//        private String role;
//
//        public Builder withName(String name) {
//            this.name = name;
//            return this;
//        }
//        public GameLogModel.Builder withGameId(String gameId) {
//            this.gameId = gameId;
//            return this;
//        }
//    }
//}
