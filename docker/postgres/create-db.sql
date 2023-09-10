CREATE DATABASE "recipes";
\c recipes

CREATE TABLE book(id bigint NOT NULL, publishingdate date, title character varying(255), tenant character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE SCHEMA tenant1;
CREATE TABLE tenant1.book(id bigint NOT NULL, publishingdate date, title character varying(255), tenant character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE SCHEMA tenant2;
CREATE TABLE tenant2.book(id bigint NOT NULL, publishingdate date, title character varying(255), tenant character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE SEQUENCE Book_SEQ INCREMENT 50 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE tenant1.Book_SEQ INCREMENT 50 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE tenant2.Book_SEQ INCREMENT 50 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;