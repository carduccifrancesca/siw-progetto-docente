insert into users (id, nome, cognome) values (10, 'mario', 'rossi');

insert into credentials (id, password, role, username, user_id) values (10, '$2a$10$J5qGiKRYXc/iYi5.c/kId.TC9Y7nKLmKedusq1C1xp2heGGBVcr8W', 'ADMIN', 'mariorossi', 10);

insert into ingrediente(id, nome, origine, descrizione) values (1, 'pachino', 'italia', 'pomodoro');
insert into ingrediente(id, nome, origine, descrizione) values (2, 'pane di lariano', 'italia', 'pane');
insert into ingrediente(id, nome, origine, descrizione) values (3, 'salmone affumicato', 'norvegia', 'pesce');

insert into piatto(id, nome, descrizione) values (1, 'bruschetta al pomodoro', 'antipasto');
insert into piatto(id, nome, descrizione) values (2, 'salmone affumicato', 'antipasto');

insert into piatto_ingredienti(piatto_id, ingredienti_id) values (1, 1);
insert into piatto_ingredienti(piatto_id, ingredienti_id) values (1, 2);
insert into piatto_ingredienti(piatto_id, ingredienti_id) values (2, 3);