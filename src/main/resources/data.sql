INSERT INTO users (username, password, email, enabled) VALUES ('user', '$2a$12$JUywmGyUd/A7d1KdErBjUOPmFiLdvXsuoWM2gIO4QHVBM2iLW6a.q','user@test.nl', TRUE);
INSERT INTO users (username, password, email, enabled) VALUES ('docent', '$2a$12$IHCy0ou8v13ebD9xtrxeAeTgo8sLcLCpolORx9KZajVhaKj1qsGWi', 'docent@test.nl', TRUE);
INSERT INTO users (username, password, email, enabled) VALUES ('admin', '$2a$12$amF2G3hTuO1JvcmwCH1E6eRvnJq1VKh9K1sAXklAebi4XP3GfYgl.', 'admin@test.nl', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('docent', 'ROLE_DOCENT');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
-- admin is een docent die alles van iedereen kan inzien en aanpassen.

INSERT INTO wordlists (title) VALUES ('dieren');

INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'aap');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'slang');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'kat');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'vogel');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'eekhoorn');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'tijger');
INSERT INTO word_list_words (word_list_title, words) VALUES ('dieren', 'dolfijn');