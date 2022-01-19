CREATE SCHEMA if not exists countries;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS continent;

CREATE TABLE continent
(
	id     INT AUTO_INCREMENT PRIMARY KEY,
	name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL
);

CREATE TABLE country
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    continent_Id  INT,
    name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL,
    devise VARCHAR(250) DEFAULT NULL,
    greetings VARCHAR(250) DEFAULT NULL,
    FOREIGN KEY(continent_Id) REFERENCES continent(id)
);

