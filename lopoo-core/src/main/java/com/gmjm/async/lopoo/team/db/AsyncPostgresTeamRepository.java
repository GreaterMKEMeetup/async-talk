package com.gmjm.async.lopoo.team.db;

import com.github.pgasync.Db;
import com.github.pgasync.Row;
import com.gmjm.async.lopoo.team.*;
import rx.Observable;

public class AsyncPostgresTeamRepository implements TeamRepository {

    private final Db db;

    public AsyncPostgresTeamRepository(Db db) {
        this.db = db;
    }

    @Override
    public Observable<Team> findAll() {
        return db.queryRows("SELECT * FROM teams")
            .map(this::fromRow);
    }

    private Team fromRow(Row row) {
        String id = row.getString("team_id");
        Integer year = row.getInt("year_id");
        String name = row.getString("name");
        Integer rank = row.getInt("rank");

        Integer wins = row.getInt("wins");
        Integer losses = row.getInt("losses");
        Record record = new Record(wins, losses);

        Integer atBats = row.getInt("at_bats");
        Integer hits = row.getInt("hits");
        Integer doubles = row.getInt("doubles");
        Integer triples = row.getInt("triples");
        Integer homeRuns = row.getInt("home_runs");
        Integer walks = row.getInt("walks");
        Integer strikeOuts = row.getInt("strike_outs");
        Batting batting = new Batting(atBats, hits, doubles, triples, homeRuns, walks, strikeOuts);

        Integer stolenBases = row.getInt("stolen_bases");
        Integer runs = row.getInt("runs");
        Running running = new Running(stolenBases, runs);

        Double era = row.getDouble("era");
        Pitching pitching = new Pitching(era);

        return new Team(id, year, name, rank, record, batting, running, pitching);
    }

}
