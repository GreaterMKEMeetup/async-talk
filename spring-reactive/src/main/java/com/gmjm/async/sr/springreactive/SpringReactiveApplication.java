package com.gmjm.async.sr.springreactive;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import com.gmjm.async.team.Team;
import com.gmjm.async.team.TeamRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.RxReactiveStreams;

@SpringBootApplication
public class SpringReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveApplication.class, args);
	}


	@RestController
	public static class TeamController {

	    private final TeamRepository teamRepository;

	    @Autowired
        public TeamController(TeamRepository teamRepository) {
	        this.teamRepository = teamRepository;
        }

        @GetMapping("/teams")
        public Publisher<Team> findAll() {
            return RxReactiveStreams.toPublisher(teamRepository.findAll());
        }

        @GetMapping("/teams/{name}")
        public Publisher<Team> findTeam(@PathVariable("name") String name) {
	        String lowerCaseName = name.toLowerCase();
	        Observable<Team> teamObservable = teamRepository.findAll()
                .filter(team -> team.getName().toLowerCase().equals(lowerCaseName) || team.getName().toLowerCase().contains(lowerCaseName));

            return RxReactiveStreams.toPublisher(teamObservable);
        }

	}

	@Bean
	public Db db(@Value("${postgres.host}") String hostname, @Value("${postgres.port}") int port, @Value("${postgres.db}") String database,
                 @Value("${postgres.username}") String username, @Value("${postgres.password}") String password) {
		return new ConnectionPoolBuilder()
			.hostname(hostname)
			.port(port)
			.database(database)
			.username(username)
			.password(password)
		.build();
	}

}
