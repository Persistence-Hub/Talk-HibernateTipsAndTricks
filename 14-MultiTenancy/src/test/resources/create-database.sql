CREATE SCHEMA tenant1;
CREATE TABLE tenant1.book(id bigint NOT NULL, bookdescription jsonb, publishingdate date, title character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE SCHEMA tenant2;
CREATE TABLE tenant2.book(id bigint NOT NULL, bookdescription jsonb, publishingdate date, title character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE SEQUENCE tenant1.hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE tenant2.hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;