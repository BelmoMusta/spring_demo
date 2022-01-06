--truncate schema public restart identity and commit no check
INSERT INTO continent(name, code) VALUES('Europe', 'eu');
INSERT INTO continent(name, code) VALUES('Africa', 'af');
INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='eu'), 'France', 'fr', 'EURO','Bonjour');
INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='eu'), 'Spain', 'es', 'EURO','Hola');
INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='eu'), 'England', 'en', 'GBP','Hello');
INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='eu'), 'Germany', 'de', 'EURO','Halo');

INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='af'), 'Morocco', 'ma', 'DH','SALAM');
INSERT INTO country(continentId, name, code, devise, greetings) VALUES((SELECT id FROM continent WHERE code='af'), 'EGYPT', 'eg', 'EP','SALAM');