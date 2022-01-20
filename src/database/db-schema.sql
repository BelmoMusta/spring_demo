CREATE SCHEMA if not exists countries;
DROP TABLE IF EXISTS country;

CREATE TABLE country
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL,
    devise VARCHAR(250) DEFAULT NULL,
    greetings VARCHAR(250) DEFAULT NULL,
    continentcode VARCHAR(250) NOT NULL
);
CREATE SCHEMA if not exists continents;
DROP TABLE IF EXISTS continents;

CREATE TABLE continents
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    continentname   VARCHAR(250) NOT NULL,
    continentcode   VARCHAR(250) NOT NULL
);