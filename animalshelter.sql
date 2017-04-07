--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animaltable; Type: TABLE; Schema: public; Owner: JamesHartanto
--

CREATE TABLE animaltable (
    id integer NOT NULL,
    name character varying(25) NOT NULL,
    species character varying(25) NOT NULL,
    breed character varying(25),
    description character varying(75) NOT NULL
);


ALTER TABLE animaltable OWNER TO "JamesHartanto";

--
-- Name: animaltable_id_seq; Type: SEQUENCE; Schema: public; Owner: JamesHartanto
--

CREATE SEQUENCE animaltable_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animaltable_id_seq OWNER TO "JamesHartanto";

--
-- Name: animaltable_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: JamesHartanto
--

ALTER SEQUENCE animaltable_id_seq OWNED BY animaltable.id;


--
-- Name: animaltable id; Type: DEFAULT; Schema: public; Owner: JamesHartanto
--

ALTER TABLE ONLY animaltable ALTER COLUMN id SET DEFAULT nextval('animaltable_id_seq'::regclass);


--
-- Data for Name: animaltable; Type: TABLE DATA; Schema: public; Owner: JamesHartanto
--

COPY animaltable (id, name, species, breed, description) FROM stdin;
\.


--
-- Name: animaltable_id_seq; Type: SEQUENCE SET; Schema: public; Owner: JamesHartanto
--

SELECT pg_catalog.setval('animaltable_id_seq', 1, false);


--
-- Name: animaltable animaltable_pkey; Type: CONSTRAINT; Schema: public; Owner: JamesHartanto
--

ALTER TABLE ONLY animaltable
    ADD CONSTRAINT animaltable_pkey PRIMARY KEY (id);


--
-- Name: animaltable_id_uindex; Type: INDEX; Schema: public; Owner: JamesHartanto
--

CREATE UNIQUE INDEX animaltable_id_uindex ON animaltable USING btree (id);


--
-- PostgreSQL database dump complete
--

