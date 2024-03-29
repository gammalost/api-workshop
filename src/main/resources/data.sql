CREATE TABLE IF NOT EXISTS USERS (ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, NAME VARCHAR(128), AGE INT);

INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Olav Olsen', 42);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Ingrid Johansen', 34);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Lars Nilsen', 53);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Sofie Kristoffersen', 28);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Magnus Eriksen', 46);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Emma Andersen', 22);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Henrik Larsen', 31);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Nora Berg', 40);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'William Carlsen', 56);
INSERT INTO USERS (ID, NAME, AGE) VALUES (DEFAULT, 'Mia Solberg', 25);

