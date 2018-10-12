package com.gmjm.async.team;

public class Team {

    private final String id;
    private final Integer year;
    private final String name;
    private final Integer rank;

    private final Record record;
    private final Batting batting;
    private final Running running;
    private final Pitching pitching;

    public Team(String id, Integer year, String name, Integer rank, Record record, Batting batting, Running running,
                Pitching pitching) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.rank = rank;

        this.record = record;
        this.batting = batting;
        this.running = running;
        this.pitching = pitching;
    }

    public String getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public Integer getRank() {
        return rank;
    }

    public Record getRecord() {
        return record;
    }

    public Batting getBatting() {
        return batting;
    }

    public Running getRunning() {
        return running;
    }

    public Pitching getPitching() {
        return pitching;
    }
}
