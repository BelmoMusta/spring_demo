INSERT INTO Continent(id,name, code) VALUES (1,'Europe','eur');
INSERT INTO Continent(id,name, code) VALUES (2,'Australia','aus');
INSERT INTO Continent(id,name, code) VALUES (3,'Africa','afr');
INSERT INTO Continent(id,name, code) VALUES (4,'Asia','asia');
INSERT INTO Continent(id,name, code) VALUES (5,'America','ame');

INSERT INTO country(name, code, devise, greetings,continent_id) VALUES('France', 'fr', 'EURO','Bonjour',1);
INSERT INTO country(name, code, devise, greetings,continent_id) VALUES('Spain', 'es', 'EURO','Hola',1);
INSERT INTO country(name, code, devise, greetings,continent_id) VALUES('England', 'en', 'GBP','Hello',1);
INSERT INTO country(name, code, devise, greetings,continent_id) VALUES('Germany', 'de', 'EURO','Halo',1);
INSERT INTO country(name, code, devise, greetings,continent_id) VALUES('Morocco', 'ma', 'MAD','Salam',3);