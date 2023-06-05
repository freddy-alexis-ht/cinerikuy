INSERT INTO genre (genre) VALUES ('Acción');
INSERT INTO genre (genre) VALUES ('Aventura');
INSERT INTO genre (genre) VALUES ('Comedia');
INSERT INTO genre (genre) VALUES ('Infantil');
INSERT INTO genre (genre) VALUES ('Drama');
INSERT INTO genre (genre) VALUES ('Terror');

INSERT INTO language (language) VALUES ('Español');
INSERT INTO language (language) VALUES ('Inglés (Sub)');
INSERT INTO language (language) VALUES ('Quechua (Sub)');

INSERT INTO situation (situation) VALUES ('Pasado');
INSERT INTO situation (situation) VALUES ('Estreno');
INSERT INTO situation (situation) VALUES ('Próximo');

INSERT INTO vote (vote) VALUES ('Votación');
INSERT INTO vote (vote) VALUES ('No-Votación');
INSERT INTO vote (vote) VALUES ('Ganador');

INSERT INTO movie (movie_code, name, duration, image_url, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M001', 'Domingo 007', '120 min', 'http://abc', 'http://def', 'Resumen de la película', 'f', 'Director 1', 'Actor 1, Actor 2', '2:30,5:30', 'C01,C02', 't', 1, 1, 2, 2);
INSERT INTO movie (movie_code, name, duration, image_url, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M002', 'Lucas en Machupicchu', '130 min', 'http://abc', 'http://def', 'Resumen de la película', 't', 'Director 1', 'Actor 1, Actor 2', '2:30,5:30', 'C01,C03', 't', 2, 2, 2, 1);
INSERT INTO movie (movie_code, name, duration, image_url, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M003', 'Domingo en Pucallpa', '140 min', 'http://abc', 'http://def', 'Resumen de la película', 't', 'Director 1', 'Actor 1, Actor 2', '2:30,5:30', 'C02,C03', 't', 3, 3, 2, 1);
INSERT INTO movie (movie_code, name, duration, image_url, trailer_url, synopsis, peruvian, director, actors, schedules, cinema_codes, enabled, genre_id, language_id, situation_id, vote_id)
VALUES ('M004', 'Domingo sin Fin', '150 min', 'http://abc', 'http://def', 'Resumen de la película', 'f', 'Director 1', 'Actor 1, Actor 2', '2:30,5:30', 'C01', 'f', 4, 1, 3, 2);

INSERT INTO voting (movie_id, username)
VALUES (2, 'Domingo7');

