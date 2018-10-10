package com.gmjm.async.ratpack;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import com.gmjm.async.lopoo.team.Team;
import com.gmjm.async.lopoo.team.TeamRepository;
import com.gmjm.async.lopoo.team.db.AsyncPostgresTeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

import java.util.HashSet;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        Db db = new ConnectionPoolBuilder()
            .hostname("localhost")
            .port(5432)
            .database("baseball")
            .username("postgres")
            .password("admin123")
            .build();
        TeamRepository teamRepository = new AsyncPostgresTeamRepository(db);

        RxRatpack.initialize();

        RatpackServer.start(server -> server
            .handlers(chain -> chain
                .get(ctx -> ctx.render("Hello World!"))
                .get("teams", ctx -> {
                    RxRatpack.fork(teamRepository.findAll())
                        .reduce(new HashSet<Team>(), (set, team) -> {
                            set.add(team);
                            return set;
                        })
                        .subscribe(set -> {
                            logger.info("Set size: {}", set.size());
                            ctx.render(Jackson.json(set));
                        });
                })
            )
        );

    }
}
