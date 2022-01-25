INSERT INTO country(name, code, devise, greetings) VALUES('France', 'fr', 'EURO','Bonjour');
INSERT INTO country(name, code, devise, greetings) VALUES('Spain', 'es', 'EURO','Hola');
INSERT INTO country(name, code, devise, greetings) VALUES('England', 'en', 'GBP','Hello');
INSERT INTO country(name, code, devise, greetings) VALUES('Germany', 'de', 'EURO','Halo');

INSERT INTO continent(code,name ) VALUES ('AF', 'Africa');
INSERT INTO continent(code,name) VALUES ('AN', 'Antarctica');
INSERT INTO continent(code,name) VALUES ('AS', 'Asia');
INSERT INTO continent(code,name) VALUES ('EU', 'Europe');
INSERT INTO continent(code,name) VALUES ('NA', 'North America');
INSERT INTO continent(code,name) VALUES ('OC', 'Oceania');
INSERT INTO continent(code,name) VALUES ('SA', 'South America');

update country set CountinentId=4 where code='fr';
update country set CountinentId=4 where code='es';
update country set CountinentId=4 where code='en';
update country set CountinentId=4 where code='de';