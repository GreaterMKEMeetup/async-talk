package com.gmjm.async.team;

import rx.Observable;

public interface TeamRepository {

    Observable<Team> findAll();

}
