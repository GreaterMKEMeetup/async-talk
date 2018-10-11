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
import ratpack.service.Service;
import ratpack.service.StopEvent;

import java.util.HashSet;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static class DbService implements Service {

        private final Db db;

        DbService(Db db) {
            this.db = db;
        }

        @Override
        public void onStop(StopEvent event) throws Exception {
            logger.info("stopping db");
            db.close();
        }
    }


    public static void main(String... args) throws Exception {
        Db db = new ConnectionPoolBuilder()
            .hostname("localhost")
            .port(5432)
            .database("baseball")
            .username("postgres")
            .password("admin123")
            .build();

        DbService dbService = new DbService(db);

        TeamRepository teamRepository = new AsyncPostgresTeamRepository(db);

        RxRatpack.initialize();

        RatpackServer.start(server -> server
            .registryOf(r -> r.add(dbService))
            .handlers(chain -> chain
                .get(ctx -> ctx.render("Hello World!"))
                .get("teams", ctx -> {
                    RxRatpack.bindExec(teamRepository.findAll())
                        .toList()
                        .subscribe(teams -> ctx.render(Jackson.json(teams)));
                })
                .get("teams/:name", ctx -> {
                    String teamName = ctx.getPathTokens().get("name").toLowerCase();
                    RxRatpack.bindExec(teamRepository.findAll())
                        .filter(team -> team.getName().toLowerCase().equals(teamName) || team.getName().toLowerCase().contains(teamName))
                        .toList()
                        .subscribe(teams -> ctx.render(Jackson.json(teams)));
                })
            )
        );

    }
}
