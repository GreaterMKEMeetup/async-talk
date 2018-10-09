package com.gmjm.async.lopoo.team;

public class Running {

    private final Integer stolenBases;
    private final Integer runs;

    public Running(Integer stolenBases, Integer runs) {
        this.stolenBases = stolenBases;
        this.runs = runs;
    }

    public Integer getStolenBases() {
        return stolenBases;
    }

    public Integer getRuns() {
        return runs;
    }
}
