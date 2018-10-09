package com.gmjm.async.lopoo.team;

public class Record {

    private final Integer wins;
    private final Integer losses;

    public Record(Integer wins, Integer losses) {
        this.wins = wins;
        this.losses = losses;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }
}
