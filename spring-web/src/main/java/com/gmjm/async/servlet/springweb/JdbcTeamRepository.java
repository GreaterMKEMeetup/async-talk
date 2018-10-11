package com.gmjm.async.servlet.springweb;

import com.gmjm.async.lopoo.team.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTeamRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTeamRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Team> findAll() {
        return jdbcTemplate.query("SELECT * FROM teams", this::fromRow);
    }

    private Team fromRow(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("team_id");
        Integer year = rs.getInt("year_id");
        String name = rs.getString("name");
        Integer rank = rs.getInt("rank");

        Integer wins = rs.getInt("wins");
        Integer losses = rs.getInt("losses");
        Record record = new Record(wins, losses);

        Integer atBats = rs.getInt("at_bats");
        Integer hits = rs.getInt("hits");
        Integer doubles = rs.getInt("doubles");
        Integer triples = rs.getInt("triples");
        Integer homeRuns = rs.getInt("home_runs");
        Integer walks = rs.getInt("walks");
        Integer strikeOuts = rs.getInt("strike_outs");
        Batting batting = new Batting(atBats, hits, doubles, triples, homeRuns, walks, strikeOuts);

        Integer stolenBases = rs.getInt("stolen_bases");
        Integer runs = rs.getInt("runs");
        Running running = new Running(stolenBases, runs);

        Double era = rs.getDouble("era");
        Pitching pitching = new Pitching(era);

        return new Team(id, year, name, rank, record, batting, running, pitching);
    }

}