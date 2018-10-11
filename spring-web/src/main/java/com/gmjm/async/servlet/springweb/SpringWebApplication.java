package com.gmjm.async.servlet.springweb;

import com.gmjm.async.lopoo.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

	@RestController
	public static class TeamController {

		private final JdbcTeamRepository teamRepository;

		@Autowired
		public TeamController(JdbcTeamRepository teamRepository) {
			this.teamRepository = teamRepository;
		}

		@GetMapping("/teams")
		public List<Team> findAll() {
			return teamRepository.findAll();
		}

		@GetMapping("/teams/{name}")
		public List<Team> findTeam(@PathVariable("name") String name) {
			String lowerCaseName = name.toLowerCase();
			return teamRepository.findAll()
				.stream()
				.filter(team -> team.getName().toLowerCase().equals(lowerCaseName) || team.getName().toLowerCase().contains(lowerCaseName))
				.collect(Collectors.toList());
		}

	}

}
