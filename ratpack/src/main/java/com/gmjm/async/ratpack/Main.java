package com.gmjm.async.ratpack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.exec.Blocking;
import ratpack.jackson.Jackson;
import ratpack.server.RatpackServer;
import ratpack.service.Service;
import ratpack.service.StopEvent;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    static class DataSourceService implements Service {

        @Override
        public void onStop(StopEvent event) throws Exception {
            logger.info("stopping db");
            DataSource.close();
        }
    }


    public static void main(String... args) throws Exception {
        JdbcTeamRepository jdbcTeamRepository = new JdbcTeamRepository();
        DataSourceService dbService = new DataSourceService();

        RatpackServer.start(server -> server
            .registryOf(r -> r.add(dbService))
            .handlers(chain -> chain
                .get(ctx -> ctx.render("Hello World!"))
                .get("teams", ctx -> {
                    Blocking.get(jdbcTeamRepository::findAll)
                        .then(teams -> ctx.render(Jackson.json(teams)));
                })
                .get("teams/:name", ctx -> {
                    String teamName = ctx.getPathTokens().get("name").toLowerCase();
                    Blocking.get(() -> jdbcTeamRepository.findByTeamName(teamName))
                        .then(teams -> ctx.render(Jackson.json(teams)));
                })
            )
        );

    }
}
