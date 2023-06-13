-----------------
-- DB CREATION --
-----------------

DROP DATABASE IF EXISTS cinerikuy WITH (FORCE);

CREATE DATABASE "cinerikuy"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
