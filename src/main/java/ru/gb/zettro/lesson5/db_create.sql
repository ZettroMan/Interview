-- Table: temp.sample_entities

-- DROP TABLE temp.sample_entities;

CREATE TABLE temp.sample_entities
(
    id bigint NOT NULL DEFAULT nextval('temp.sample_entities_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT sample_entities_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE temp.sample_entities
    OWNER to postgres;