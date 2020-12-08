insert into users (id, code, email, is_moderator, name, password, photo, reg_time)
values (1, null, 'first@mail.com', 1, 'Harry Potter', 'pass', null, CURRENT_TIMESTAMP);

insert into users (id, code, email, is_moderator, name, password, photo, reg_time)
values (2, null, 'second@mail.com', 0, 'Ronald Weasley', '123', null, CURRENT_TIMESTAMP);

insert into posts (id, is_active, text, time, title, view_count, moderator_id, user_id)
values (1, 1, 'Текст поста бла бла бла', CURRENT_TIMESTAMP, 'Заголовок этого поста',
2, 1, 2);

insert into post_comments (id, text, time, post_id, user_id)
values (1, 'Гневный комментарий', CURRENT_TIMESTAMP,  1, 2);

insert into tags (id, name)
values (1, '#блабла');

insert into tags2posts (id, post_id, tag_id)
values (1, 1, 1);



