package com.gmjm.async.lopoo.team;

import rx.Observable;

public interface TeamRepository {

    Observable<Team> findAll();

}
