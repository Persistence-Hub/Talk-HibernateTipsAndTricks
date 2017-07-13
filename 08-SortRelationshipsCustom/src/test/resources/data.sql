INSERT INTO book (id, title, version) VALUES (1, 'B Book', 0);
INSERT INTO book (id, title, version) VALUES (2, 'C Book', 0);
INSERT INTO book (id, title, version) VALUES (3, 'A Book', 0);

INSERT INTO author (id, name, version) VALUES (1, 'B Author', 0);
INSERT INTO author (id, name, version) VALUES (2, 'A Author', 0);
INSERT INTO author (id, name, version) VALUES (3, 'C Author', 0);

INSERT INTO book_author (fk_book, fk_author) VALUES (1,1);
INSERT INTO book_author (fk_book, fk_author) VALUES (1,2);
INSERT INTO book_author (fk_book, fk_author) VALUES (1,3);
INSERT INTO book_author (fk_book, fk_author) VALUES (2,1);
INSERT INTO book_author (fk_book, fk_author) VALUES (2,2);
INSERT INTO book_author (fk_book, fk_author) VALUES (2,3);
INSERT INTO book_author (fk_book, fk_author) VALUES (3,1);
INSERT INTO book_author (fk_book, fk_author) VALUES (3,2);
INSERT INTO book_author (fk_book, fk_author) VALUES (3,3);
