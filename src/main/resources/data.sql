INSERT INTO users (username, password, email, enabled) VALUES ('TestLeerling', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq','leerling@test.nl', TRUE); --password = password
INSERT INTO users (username, password, email, enabled) VALUES ('TestDocent', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq', 'docent@test.nl', TRUE); --password = password
INSERT INTO users (username, password, email, enabled) VALUES ('Mirte123', '$2a$12$tnei4b1FWZ4YXmJSnADp.eQjwWU8Jni6NlTu1C9Ac0mD5hAAtskwq','mirte@test.nl', TRUE); --password = password

INSERT INTO authorities (username, authority) VALUES ('TestLeerling', 'STUDENT');
INSERT INTO authorities (username, authority) VALUES ('TestDocent', 'TEACHER');
INSERT INTO authorities (username, authority) VALUES ('Mirte123', 'STUDENT');
-- Extra for later: admin is een docent die alles van iedereen kan inzien en aanpassen.

INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES (1001, 'Jantje', 'Jansen', 11, 'Montessori School', 'TestLeerling', null);
INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES (1002, 'Piet', 'Pietersen', null, 'Freelancer', 'TestDocent', null);
INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES (1003, 'Mirte', 'Houwing', 28, 'Novi Hogeschool', 'Mirte123', null);

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

INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (1, 5, TRUE, null, 'dieren', 1001);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (2, 10, FALSE, null, 'dieren', 1001);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (3, 8, FALSE, null, 'kleuren', 1001);
INSERT INTO exams (id, wrong_entries, is_passed, timestamp, wordlist_title, userprofile_id) VALUES (4, 3, TRUE, null, 'dieren', 1003);



-- INSERT INTO user_profiles (id, first_name, last_name, age, school, profile_pic_id) VALUES (51, 'Jantje', 'Jansen', 11, 'Montessori School', null);
-- INSERT INTO user_profiles (id, first_name, last_name, age, school, profile_pic_id) VALUES (52, 'Piet', 'Pietersen', null, 'Freelancer', null);
-- INSERT INTO user_profiles (id, first_name, last_name, age, school, profile_pic_id) VALUES (53, 'Mirte', 'Houwing', 28, 'Novi Hogeschool', null);

-- INSERT INTO users (username, password, email, enabled, user_profile_id) VALUES ('TestLeerling', '$2a$12$JUywmGyUd/A7d1KdErBjUOPmFiLdvXsuoWM2gIO4QHVBM2iLW6a.q','leerling@test.nl', TRUE, 51); --password: userpassword
-- INSERT INTO users (username, password, email, enabled, user_profile_id) VALUES ('TestDocent', '$2a$12$IHCy0ou8v13ebD9xtrxeAeTgo8sLcLCpolORx9KZajVhaKj1qsGWi', 'docent@test.nl', TRUE, 52); --password: docentpassword
-- INSERT INTO users (username, password, email, enabled, user_profile_id) VALUES ('Mirte123', '$2a$12$JUywmGyUd/A7d1KdErBjUOPmFiLdvXsuoWM2gIO4QHVBM2iLW6a.q','mirte@test.nl', TRUE, 53); --password: userpassword
