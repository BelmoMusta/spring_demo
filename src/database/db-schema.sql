CREATE SCHEMA if not exists countries;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS Continent;

CREATE TABLE Continent
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL
);

CREATE TABLE country
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL,
    devise VARCHAR(250) DEFAULT NULL,
    greetings VARCHAR(250) DEFAULT NULL,
    continent_id INT,
    CONSTRAINT continent_id_constraint FOREIGN KEY (continent_id) REFERENCES Continent (id)
);