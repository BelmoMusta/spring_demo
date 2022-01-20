INSERT INTO Continent(id,name, code) VALUES (1,'Europe','eur');
INSERT INTO Continent(id,name, code) VALUES (2,'Australie','aus');
INSERT INTO Continent(id,name, code) VALUES (3,'Afrique','afr');
INSERT INTO Continent(id,name, code) VALUES (4,'Asia','asia');
INSERT INTO Continent(id,name, code) VALUES (5,'Amerique','ame');

INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES('France', 'fr', 'EURO','Bonjour',1);
INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES('Spain', 'es', 'EURO','Hola',1);
INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES('England', 'en', 'GBP','Hello',1);
INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES('Germany', 'de', 'EURO','Halo',1);
INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES('Morocco', 'ma', 'MAD','Salam',3);