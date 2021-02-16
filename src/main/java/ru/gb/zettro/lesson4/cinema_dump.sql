--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 13.1

-- Started on 2021-02-13 23:10:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 10 (class 2615 OID 19093)
-- Name: cinema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA cinema;


ALTER SCHEMA cinema OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 236 (class 1259 OID 19096)
-- Name: films; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.films (
    id bigint NOT NULL,
    title character varying(256) NOT NULL,
    duration integer NOT NULL
);


ALTER TABLE cinema.films OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 19094)
-- Name: films_id_seq; Type: SEQUENCE; Schema: cinema; Owner: postgres
--

CREATE SEQUENCE cinema.films_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.films_id_seq OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 235
-- Name: films_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: postgres
--

ALTER SEQUENCE cinema.films_id_seq OWNED BY cinema.films.id;


--
-- TOC entry 237 (class 1259 OID 19104)
-- Name: sessions; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.sessions (
    id bigint NOT NULL,
    film_id bigint NOT NULL,
    start_time time without time zone NOT NULL,
    price integer NOT NULL
);


ALTER TABLE cinema.sessions OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 19145)
-- Name: sessions_id_seq; Type: SEQUENCE; Schema: cinema; Owner: postgres
--

CREATE SEQUENCE cinema.sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.sessions_id_seq OWNER TO postgres;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 240
-- Name: sessions_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: postgres
--

ALTER SEQUENCE cinema.sessions_id_seq OWNED BY cinema.sessions.id;


--
-- TOC entry 239 (class 1259 OID 19120)
-- Name: tickets; Type: TABLE; Schema: cinema; Owner: postgres
--

CREATE TABLE cinema.tickets (
    id bigint NOT NULL,
    session_id bigint NOT NULL,
    customer_name character varying(256),
    optional_data character varying(256)
);


ALTER TABLE cinema.tickets OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 19118)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: cinema; Owner: postgres
--

CREATE SEQUENCE cinema.tickets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cinema.tickets_id_seq OWNER TO postgres;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 238
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: cinema; Owner: postgres
--

ALTER SEQUENCE cinema.tickets_id_seq OWNED BY cinema.tickets.id;


--
-- TOC entry 2784 (class 2604 OID 19099)
-- Name: films id; Type: DEFAULT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.films ALTER COLUMN id SET DEFAULT nextval('cinema.films_id_seq'::regclass);


--
-- TOC entry 2785 (class 2604 OID 19147)
-- Name: sessions id; Type: DEFAULT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.sessions ALTER COLUMN id SET DEFAULT nextval('cinema.sessions_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 19123)
-- Name: tickets id; Type: DEFAULT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.tickets ALTER COLUMN id SET DEFAULT nextval('cinema.tickets_id_seq'::regclass);


--
-- TOC entry 2923 (class 0 OID 19096)
-- Dependencies: 236
-- Data for Name: films; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

COPY cinema.films (id, title, duration) FROM stdin;
1	Пираты Карибского моря	135
2	Матрица	120
3	Властелин колец	100
4	Особое мнение	95
5	Остров проклятых	105
6	Рэмбо. Первая кровь	90
7	Полет навигатора	85
8	Форрест Гамп	125
9	Назад в будущее	110
10	Назад в будущее - 2	105
11	Назад в будущее - 3	115
12	Терминатор	100
13	Унесенные ветром	180
14	Слияние двух лун	100
\.


--
-- TOC entry 2924 (class 0 OID 19104)
-- Dependencies: 237
-- Data for Name: sessions; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

COPY cinema.sessions (id, film_id, start_time, price) FROM stdin;
1	3	07:00:00	250
2	6	08:50:00	150
3	7	09:20:00	170
4	1	10:50:00	200
5	10	12:20:00	350
6	8	13:40:00	180
7	9	16:00:00	220
8	11	18:00:00	235
10	5	21:00:00	350
11	14	23:00:00	450
9	8	19:30:00	400
\.


--
-- TOC entry 2926 (class 0 OID 19120)
-- Dependencies: 239
-- Data for Name: tickets; Type: TABLE DATA; Schema: cinema; Owner: postgres
--

COPY cinema.tickets (id, session_id, customer_name, optional_data) FROM stdin;
9	4	Bob	Marley
10	6	Robert	Martin
11	8	Robert	Sedgewick
12	7	Robert	De Niro
13	9	Robert	Downey Jr.
14	4	Bobby	Fisher
15	8	Robert	Rodriguez
16	6	Alan	Parker
17	11	Antony	Hopkins
18	2	John	Malkovich
19	1	Janet	Jackson
20	4	Wesley	So
21	7	Magnus	Carlsen
22	7	Laura	Branigan
23	8	Semen	Slepakov
24	3	Semen	Treskunov
25	5	Mikhail	Galustyan
26	2	Suzi	Quattro
27	9	Frank	Sinatra
28	11	Michael	Bolton
29	10	George	Michael
30	4	Iggy	Pop
31	8	Kevin	Cosnter
32	9	Arnold	Schwarzenegger
33	1	Fabiano	Caruana
34	1	Jessica	Alba
35	9	Michael	Jordan
36	10	Sam	Smith
37	2	Andrea	Bochelli
38	7	Mike	Tayson
39	8	Lotfi	Lamaali
\.


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 235
-- Name: films_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: postgres
--

SELECT pg_catalog.setval('cinema.films_id_seq', 14, true);


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 240
-- Name: sessions_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: postgres
--

SELECT pg_catalog.setval('cinema.sessions_id_seq', 13, true);


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 238
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: cinema; Owner: postgres
--

SELECT pg_catalog.setval('cinema.tickets_id_seq', 39, true);


--
-- TOC entry 2788 (class 2606 OID 19101)
-- Name: films films_pkey; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.films
    ADD CONSTRAINT films_pkey PRIMARY KEY (id);


--
-- TOC entry 2791 (class 2606 OID 19152)
-- Name: sessions sessions_pkey; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.sessions
    ADD CONSTRAINT sessions_pkey PRIMARY KEY (id);


--
-- TOC entry 2793 (class 2606 OID 19125)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 2789 (class 1259 OID 19158)
-- Name: fki_sessions_films; Type: INDEX; Schema: cinema; Owner: postgres
--

CREATE INDEX fki_sessions_films ON cinema.sessions USING btree (film_id);


--
-- TOC entry 2794 (class 2606 OID 19153)
-- Name: sessions sessions_films; Type: FK CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.sessions
    ADD CONSTRAINT sessions_films FOREIGN KEY (film_id) REFERENCES cinema.films(id);


--
-- TOC entry 2795 (class 2606 OID 19159)
-- Name: tickets tickets_sessions; Type: FK CONSTRAINT; Schema: cinema; Owner: postgres
--

ALTER TABLE ONLY cinema.tickets
    ADD CONSTRAINT tickets_sessions FOREIGN KEY (session_id) REFERENCES cinema.sessions(id);


-- Completed on 2021-02-13 23:10:48

--
-- PostgreSQL database dump complete
--

