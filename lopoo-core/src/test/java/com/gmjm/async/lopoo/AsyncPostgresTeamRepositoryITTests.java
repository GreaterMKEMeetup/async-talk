package com.gmjm.async.lopoo;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import com.gmjm.async.lopoo.team.db.AsyncPostgresTeamRepository;
import com.gmjm.async.lopoo.team.Team;
import com.gmjm.async.lopoo.team.TeamRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rx.Observable;

import static org.junit.Assert.assertEquals;

public class AsyncPostgresTeamRepositoryITTests {

    private static Db db;
    private TeamRepository teamRepository;

    @BeforeClass
    public static void setupDb() throws Exception {
            db = new ConnectionPoolBuilder()
                .hostname("localhost")
                .port(5432)
                .database("baseball")
                .username("postgres")
                .password("admin123")
                .build();
    }

    @AfterClass
    public static void tearDownDb() throws Exception {
        db.close();
    }

    @Before
    public void setup() {
        teamRepository = new AsyncPostgresTeamRepository(db);
    }

    @Test
    public void findAll_ShouldReturnResults() {
        Observable<Team> teams = teamRepository.findAll();
        int count = teams.reduce(0, (acc, team) -> acc + 1)
            .toBlocking()
            .first();

        assertEquals(2865, count);
    }

}
