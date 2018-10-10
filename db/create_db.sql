-- Database: baseball

-- DROP DATABASE baseball;
CREATE DATABASE baseball
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

\c baseball

CREATE TABLE public.teams
(
    year_id integer NOT NULL,
    team_id character varying(3) COLLATE pg_catalog."default" NOT NULL,
    wins integer,
    losses integer,
    runs integer,
    at_bats integer,
    hits integer,
    doubles integer,
    triples integer,
    home_runs integer,
    walks integer,
    strike_outs integer,
    stolen_bases integer,
    era double precision,
    name character varying COLLATE pg_catalog."default",
    rank integer
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.teams
    OWNER to postgres;

COPY public.teams FROM '/teams_reduced.csv' WITH CSV HEADER DELIMITER AS ',';