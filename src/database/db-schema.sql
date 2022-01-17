CREATE SCHEMA if not exists countries;
DROP TABLE IF EXISTS country;

CREATE TABLE country
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(250) NOT NULL,
    code   VARCHAR(250) NOT NULL,
    devise VARCHAR(250) DEFAULT NULL,
    greet VARCHAR(250) DEFAULT NULL,
    continent VARCHAR(250) NOT NULL
);

CREATE TABLE continent
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    code   VARCHAR(200) NOT NULL,
    name   VARCHAR(250) NOT NULL        
);