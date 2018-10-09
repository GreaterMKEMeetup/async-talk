package com.gmjm.async.lopoo.team;

import com.gmjm.async.lopoo.team.Team;
import rx.Observable;

public interface TeamRepository {

    Observable<Team> findAll();

}
