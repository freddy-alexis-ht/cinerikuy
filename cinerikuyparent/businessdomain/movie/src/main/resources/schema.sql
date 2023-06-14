CREATE TABLE cr_genre (
   id INT AUTO_INCREMENT PRIMARY KEY,
   genre VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cr_language (
   id INT AUTO_INCREMENT PRIMARY KEY,
   language VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cr_situation (
   id INT AUTO_INCREMENT PRIMARY KEY,
   situation VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cr_vote (
   id INT AUTO_INCREMENT PRIMARY KEY,
   vote VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE cr_movie (
   id INT AUTO_INCREMENT PRIMARY KEY,
   movie_code VARCHAR(20) NOT NULL UNIQUE,
   name VARCHAR NOT NULL,
   duration VARCHAR NOT NULL,
   image_url VARCHAR NOT NULL,
   trailer_url VARCHAR NOT NULL,
   synopsis VARCHAR NOT NULL,
   peruvian BOOLEAN NOT NULL,
   director VARCHAR NOT NULL,
   actors VARCHAR NOT NULL,
   schedules VARCHAR NOT NULL,
   cinema_codes VARCHAR NOT NULL,
   enabled BOOLEAN NOT NULL,
   genre_id INT NOT NULL,
   language_id INT NOT NULL,
   situation_id INT NOT NULL,
   vote_id INT NOT NULL,
   foreign key (genre_id) references cr_genre(id),
   foreign key (language_id) references cr_language(id),
   foreign key (situation_id) references cr_situation(id),
   foreign key (vote_id) references cr_vote(id)
);

CREATE TABLE cr_voting (
    movie_id INT NOT NULL,
    username VARCHAR NOT NULL,
    foreign key(movie_id) REFERENCES cr_movie(id),
    primary key(movie_id, username)
);