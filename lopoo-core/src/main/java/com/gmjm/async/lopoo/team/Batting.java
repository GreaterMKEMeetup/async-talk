package com.gmjm.async.lopoo.team;

public class Batting {

    private final Integer atBats;
    private final Integer hits;
    private final Integer doubles;
    private final Integer triples;
    private final Integer homeRuns;
    private final Integer walks;
    private final Integer strikeOuts;

    public Batting(Integer atBats, Integer hits, Integer doubles, Integer triples, Integer homeRuns,
                   Integer walks, Integer strikeOuts) {
        this.atBats = atBats;
        this.hits = hits;
        this.doubles = doubles;
        this.triples = triples;
        this.homeRuns = homeRuns;
        this.walks = walks;
        this.strikeOuts = strikeOuts;
    }

    public Integer getAtBats() {
        return atBats;
    }

    public Integer getHits() {
        return hits;
    }

    public Integer getDoubles() {
        return doubles;
    }

    public Integer getTriples() {
        return triples;
    }

    public Integer getHomeRuns() {
        return homeRuns;
    }

    public Integer getWalks() {
        return walks;
    }

    public Integer getStrikeOuts() {
        return strikeOuts;
    }

}
