--
-- PostgreSQL database dump
--

-- Dumped from database version 13.20
-- Dumped by pg_dump version 13.20

-- Started on 2026-08-13 14:42:19

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 40996)
-- Name: brands; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brands (
    id bigint NOT NULL,
    name character varying(80) NOT NULL
);


ALTER TABLE public.brands OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 40994)
-- Name: brands_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.brands_id_seq OWNER TO postgres;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 200
-- Name: brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.brands_id_seq OWNED BY public.brands.id;


--
-- TOC entry 205 (class 1259 OID 41023)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id bigint NOT NULL,
    device_id bigint NOT NULL,
    author character varying(80) NOT NULL,
    content character varying(1000) NOT NULL,
    rating integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT comments_rating_check CHECK (((rating >= 1) AND (rating <= 5)))
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 41021)
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_id_seq OWNER TO postgres;

--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 204
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- TOC entry 203 (class 1259 OID 41006)
-- Name: devices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.devices (
    id bigint NOT NULL,
    name character varying(140) NOT NULL,
    brand_id bigint NOT NULL,
    type character varying(50) NOT NULL,
    release_date date NOT NULL,
    processor character varying(120) NOT NULL,
    memory character varying(120) NOT NULL,
    storage character varying(120) NOT NULL,
    screen character varying(80) NOT NULL,
    description character varying(1000) NOT NULL,
    image_url character varying(255) NOT NULL,
    price numeric(12,2) NOT NULL,
    CONSTRAINT devices_price_check CHECK ((price >= (0)::numeric))
);


ALTER TABLE public.devices OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 41004)
-- Name: devices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.devices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.devices_id_seq OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 202
-- Name: devices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.devices_id_seq OWNED BY public.devices.id;


--
-- TOC entry 2864 (class 2604 OID 40999)
-- Name: brands id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brands ALTER COLUMN id SET DEFAULT nextval('public.brands_id_seq'::regclass);


--
-- TOC entry 2867 (class 2604 OID 41026)
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- TOC entry 2865 (class 2604 OID 41009)
-- Name: devices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices ALTER COLUMN id SET DEFAULT nextval('public.devices_id_seq'::regclass);


--
-- TOC entry 3014 (class 0 OID 40996)
-- Dependencies: 201
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.brands (id, name) FROM stdin;
1	Samsung
2	Apple
3	Lenovo
4	Xiaomi
\.


--
-- TOC entry 3018 (class 0 OID 41023)
-- Dependencies: 205
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (id, device_id, author, content, rating, created_at) FROM stdin;
\.


--
-- TOC entry 3016 (class 0 OID 41006)
-- Dependencies: 203
-- Data for Name: devices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.devices (id, name, brand_id, type, release_date, processor, memory, storage, screen, description, image_url, price) FROM stdin;
1	Galaxy S25	1	Celular	2025-01-22	Snapdragon 8 Elite	12 GB RAM	256 GB	6.2 pulgadas AMOLED	Celular de alto rendimiento orientado a productividad, fotografía y entretenimiento.	https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80	3299000.00
2	iPhone 16	2	Celular	2024-09-20	Apple A18	8 GB RAM	128 GB	6.1 pulgadas OLED	Smartphone con enfoque en rendimiento, fotografía computacional y ecosistema Apple.	https://images.unsplash.com/photo-1592899677977-9c10ca588bbd?auto=format&fit=crop&w=900&q=80	3999000.00
3	ThinkPad X1 Carbon	3	Portátil	2025-01-07	Intel Core Ultra	16 GB RAM	512 GB SSD	14 pulgadas	Portátil empresarial diseñado para productividad, movilidad y trabajo profesional.	https://images.unsplash.com/photo-1496181133206-80ce9b88a853?auto=format&fit=crop&w=900&q=80	6899000.00
4	Xiaomi 14	4	Celular	2024-02-25	Snapdragon 8 Gen 3	12 GB RAM	512 GB	6.36 pulgadas AMOLED	Dispositivo compacto de gama alta con gran capacidad de almacenamiento y cámara avanzada.	https://images.unsplash.com/photo-1512499617640-c2f999098c01?auto=format&fit=crop&w=900&q=80	2799000.00
\.


--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 200
-- Name: brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brands_id_seq', 4, true);


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 204
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 202
-- Name: devices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.devices_id_seq', 4, true);


--
-- TOC entry 2871 (class 2606 OID 41003)
-- Name: brands brands_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_name_key UNIQUE (name);


--
-- TOC entry 2873 (class 2606 OID 41001)
-- Name: brands brands_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- TOC entry 2879 (class 2606 OID 41033)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 2875 (class 2606 OID 41015)
-- Name: devices devices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices
    ADD CONSTRAINT devices_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 1259 OID 41041)
-- Name: idx_comments_device; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_comments_device ON public.comments USING btree (device_id);


--
-- TOC entry 2876 (class 1259 OID 41039)
-- Name: idx_devices_brand; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_devices_brand ON public.devices USING btree (brand_id);


--
-- TOC entry 2877 (class 1259 OID 41040)
-- Name: idx_devices_release_date; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_devices_release_date ON public.devices USING btree (release_date DESC);


--
-- TOC entry 2882 (class 2606 OID 41034)
-- Name: comments comments_device_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_device_id_fkey FOREIGN KEY (device_id) REFERENCES public.devices(id) ON DELETE CASCADE;


--
-- TOC entry 2881 (class 2606 OID 41016)
-- Name: devices devices_brand_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.devices
    ADD CONSTRAINT devices_brand_id_fkey FOREIGN KEY (brand_id) REFERENCES public.brands(id);


-- Completed on 2026-08-13 14:42:26

--
-- PostgreSQL database dump complete
--

