INSERT INTO users (username, password, email, enabled) VALUES ('user', '$2a$12$JUywmGyUd/A7d1KdErBjUOPmFiLdvXsuoWM2gIO4QHVBM2iLW6a.q','user@test.nl', TRUE); --password: userpassword
INSERT INTO users (username, password, email, enabled) VALUES ('docent', '$2a$12$IHCy0ou8v13ebD9xtrxeAeTgo8sLcLCpolORx9KZajVhaKj1qsGWi', 'docent@test.nl', TRUE); --password: docentpassword
INSERT INTO users (username, password, email, enabled) VALUES ('admin', '$2a$12$amF2G3hTuO1JvcmwCH1E6eRvnJq1VKh9K1sAXklAebi4XP3GfYgl.', 'admin@test.nl', TRUE); ----password: adminpassword

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('docent', 'ROLE_DOCENT');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
-- admin is een docent die alles van iedereen kan inzien en aanpassen.

INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES ('01', 'Super', 'Admin', null, null , 'admin', null);
INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES ('02', 'Jantje', 'Jansen', '11', 'Montessori School', 'user', null);
INSERT INTO user_profiles (id, first_name, last_name, age, school, username, profile_pic_id) VALUES ('03', 'Piet', 'Pietersen', null, null , 'docent', null);

INSERT INTO wordlists (title) VALUES ('dieren');

INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'aap');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'slang');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'kat');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'vogel');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'eekhoorn');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'tijger');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'dolfijn');