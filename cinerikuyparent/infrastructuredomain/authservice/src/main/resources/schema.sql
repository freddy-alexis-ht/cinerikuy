create table cr_user_credential (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(8) NOT NULL,
    password VARCHAR(60) NOT NULL,
    active BOOLEAN NOT NULL,
    roles VARCHAR NOT NULL
);
