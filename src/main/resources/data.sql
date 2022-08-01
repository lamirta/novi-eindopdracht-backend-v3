INSERT INTO users (username, password, email, enabled) VALUES ('TestDocent', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq', 'docent@test.nl', TRUE); --password = password
INSERT INTO users (username, password, email, enabled) VALUES ('TestLeerling', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq','leerling@test.nl', TRUE); --password = password
INSERT INTO users (username, password, email, enabled) VALUES ('Mirte123', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq','mirte@test.nl', TRUE); --password = password

INSERT INTO authorities (username, authority) VALUES ('TestDocent', 'TEACHER');
INSERT INTO authorities (username, authority) VALUES ('TestLeerling', 'STUDENT');
INSERT INTO authorities (username, authority) VALUES ('Mirte123', 'STUDENT');
-- Extra for later: make authority Admin with all excess and Teacher limited.

INSERT INTO user_profiles (id, first_name, last_name, age, school, username) VALUES (1001, 'Piet', 'Pietersen', null, 'Freelancer', 'TestDocent');
INSERT INTO user_profiles (id, first_name, last_name, age, school, username) VALUES (1002, 'Jantje', 'Jansen', 11, 'Montessori School', 'TestLeerling');
INSERT INTO user_profiles (id, first_name, last_name, age, school, username) VALUES (1003, 'Mirte', 'Houwing', 28, 'Novi Hogeschool', 'Mirte123');



INSERT INTO wordlists (title) VALUES ('dieren');

INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'aap');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'slang');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'kat');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'vogel');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'eekhoorn');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'tijger');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'dolfijn');

INSERT INTO wordlists (title) VALUES ('kleuren');

INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'rood');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'groen');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'paars');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'geel');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'blauw');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'oranje');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'lila');
INSERT INTO word_list_words (word_list_title, words) VALUES ('kleuren', 'bordeaux');

INSERT INTO wordlists (title) VALUES ('landen');

INSERT INTO word_list_words (word_list_title, words) VALUES ('landen', 'peru');
INSERT INTO word_list_words (word_list_title, words) VALUES ('landen', 'spanje');
INSERT INTO word_list_words (word_list_title, words) VALUES ('landen', 'nederland');
INSERT INTO word_list_words (word_list_title, words) VALUES ('landen', 'canada');
INSERT INTO word_list_words (word_list_title, words) VALUES ('landen', 'china');

INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (1, 5, TRUE, '2022-01-01T12:30:30.000000', 'dieren', 1002);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (2, 10, FALSE, '2022-01-01T12:30:30.000000', 'dieren', 1002);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (3, 8, FALSE, '2022-01-01T12:30:30.000000', 'kleuren', 1002);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (4, 3, TRUE, '2022-01-01T12:30:30.000000', 'dieren', 1003);
