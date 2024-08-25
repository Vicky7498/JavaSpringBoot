package com.infy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Players {
    @Id
    @Column(name = "player_id")
    private Integer playerId;
    @Column(name = "player_name")
    private String playerName;
    private Integer ranking;
    @Column(name = "batting_style")
    private String battingStyle;
    @Column(name = "bowling_style")
    private String bowlingStyle;
    @Column(name = "debut_date")
    private String debutDate;
    private String country;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(String debutDate) {
        this.debutDate = debutDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "players{" + "playerId=" + playerId + ", playerName='" + playerName + '\'' + ", ranking=" + ranking + ", battingStyle='" + battingStyle + '\'' + ", bowlingStyle='" + bowlingStyle + '\'' + ", debutDate='" + debutDate + '\'' + ", country='" + country + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Players players)) return false;
        return Objects.equals(playerId, players.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerId);
    }
}
