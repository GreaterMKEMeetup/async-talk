-- Table: public.teams

-- DROP TABLE public.teams;

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