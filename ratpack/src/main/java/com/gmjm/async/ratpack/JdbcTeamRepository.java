package com.gmjm.async.ratpack;

import com.gmjm.async.team.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTeamRepository {

    public List<Team> findAll() throws SQLException {
        List<Team> teams = new ArrayList<>();
        try (Connection con = DataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM TEAMS");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                teams.add(fromResultSet(rs));
            }
        }
        return teams;
    }

    public List<Team> findByTeamName(String name) throws SQLException {
        List<Team> teams = new ArrayList<>();
        try (Connection con = DataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM TEAMS WHERE LOWER(name) like ?");
            statement.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                teams.add(fromResultSet(rs));
            }
        }
        return teams;
    }

    private Team fromResultSet(ResultSet rs) throws SQLException {
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
