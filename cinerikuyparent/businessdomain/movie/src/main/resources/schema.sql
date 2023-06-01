CREATE TABLE genre (
   id INT AUTO_INCREMENT PRIMARY KEY,
   genre VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE language (
   id INT AUTO_INCREMENT PRIMARY KEY,
   language VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE situation (
   id INT AUTO_INCREMENT PRIMARY KEY,
   situation VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE vote (
   id INT AUTO_INCREMENT PRIMARY KEY,
   vote VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE movie (
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
   schedules VARCHAR,
   enabled BOOLEAN NOT NULL,
   genre_id INT,
   language_id INT,
   situation_id INT,
   vote_id INT,
   foreign key (genre_id) references genre(id),
   foreign key (language_id) references language(id),
   foreign key (situation_id) references situation(id),
   foreign key (vote_id) references vote(id)
);

