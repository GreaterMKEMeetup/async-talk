package com.gmjm.async.servlet.springweb;

import com.gmjm.async.team.*;
import com.gmjm.async.team.db.DatabaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTeamRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTeamRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Team> findAll() {
        return jdbcTemplate.query("SELECT * FROM teams", DatabaseUtils::fromResultSet);
    }

    public List<Team> findByTeamName(String name) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", "%" + name.toLowerCase()+ "%");

        return jdbcTemplate.query("SELECT * FROM teams WHERE LOWER(name) like :name", parameterSource, DatabaseUtils::fromResultSet);
    }

}