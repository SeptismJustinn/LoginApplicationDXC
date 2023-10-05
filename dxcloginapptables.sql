CREATE TABLE logins (
    jti uuid NOT NULL,
    user_id uuid
);

CREATE TABLE roles (
    role_name character varying(255) NOT NULL
);


CREATE TABLE users (
    id uuid NOT NULL,
    hash character varying(255),
    name character varying(255),
    role character varying(255),
    username character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['USER'::character varying, 'ADMIN'::character varying])::text[])))
);


--
-- TOC entry 3344 (class 0 OID 41593)
-- Dependencies: 216
-- Data for Name: logins; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.logins (jti, user_id) FROM stdin;
\.


--
-- TOC entry 3343 (class 0 OID 41489)
-- Dependencies: 215
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.roles (role_name) FROM stdin;
\.


--
-- TOC entry 3345 (class 0 OID 41600)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (id, hash, name, role, username) FROM stdin;
1b37b65d-9911-4f9b-96a4-7dbee4f15012	$2a$10$TLDE2jSTGivyjntamDfQbO52GMXFYlaluCJign3mny3fdnZ1vXUuW	TestName	ADMIN	TestUsername
6a8e3c56-516a-4df9-a961-43f2ecfc1bf3	$2a$10$Zti3Y6tNvRvpMnku9tOXROjIiHQbnzVyhV3Nod7YHnFtC9egVJdR6	Another Test User	USER	diamondo
\.


--
-- TOC entry 3195 (class 2606 OID 41597)
-- Name: logins logins_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.logins
    ADD CONSTRAINT logins_pkey PRIMARY KEY (jti);


--
-- TOC entry 3197 (class 2606 OID 41599)
-- Name: logins logins_user_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.logins
    ADD CONSTRAINT logins_user_id_key UNIQUE (user_id);


--
-- TOC entry 3193 (class 2606 OID 41493)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_name);


--
-- TOC entry 3199 (class 2606 OID 41607)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 41608)
-- Name: logins fkfpt7v1v7qgn7x5bj76qse0pl2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.logins
    ADD CONSTRAINT fkfpt7v1v7qgn7x5bj76qse0pl2 FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2023-10-06 04:42:16

--
-- PostgreSQL database dump complete
--

