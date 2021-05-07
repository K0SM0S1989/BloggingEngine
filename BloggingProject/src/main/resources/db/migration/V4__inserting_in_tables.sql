insert into users (code, email, is_moderator, name, password, photo, reg_time)
values (null, 'first@mail.com', 1, 'Harry Potter', 'pass', null, CURRENT_TIMESTAMP);

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values (null, 'second@mail.com', 0, 'Ronald Weasley', '123456', null, CURRENT_TIMESTAMP);

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values (null, 'kovy@mail.com', 1, 'Kovi', '123', null, CURRENT_TIMESTAMP);

insert into users (code, email, is_moderator, name, password, photo, reg_time)
values (null, 'ovy@mail.com', 0, 'Ovi', '123', null, CURRENT_TIMESTAMP);

insert into posts (is_active, text, time, title, view_count, moderator_id, user_id)
values (1, 'Текст поста бла бла бла', CURRENT_TIMESTAMP, 'Заголовок первго поста',
2, 1, 2);

insert into posts (is_active, moderation_status, text, time, title, view_count, moderator_id, user_id)
values (1, 'ACCEPTED', 'Текст второго поста, очень длинный и должен быть более десяти слов так что пишем',  LOCALTIMESTAMP, 'Заголовок второго поста',
2, 1, 2);

insert into posts (is_active, moderation_status, text, time, title, view_count, moderator_id, user_id)
values (1, 'ACCEPTED',
'Текст третьего поста тоже должен быть длинным, так что пытаемся его удлиннить, хотя мне за это никто не платит',
 CURRENT_TIMESTAMP, 'Заголовок третьего поста',
2, 1, 2);

insert into post_comments (text, time, post_id, user_id)
values ('Гневный комментарий', CURRENT_TIMESTAMP,  1, 2);

insert into tags (name)
values ('#блабла');

insert into tags (name)
values ('#поста');

insert into tags2posts (post_id, tag_id)
values (1, 1);

insert into tags2posts (post_id, tag_id)
values (3, 2);

insert into post_votes (user_id, post_id, time, value)
values (1, 2, CURRENT_TIMESTAMP, -1);

insert into post_votes (user_id, post_id, time, value)
values (2, 3, CURRENT_TIMESTAMP, -1);

insert into post_votes (user_id, post_id, time, value)
values (1, 3, CURRENT_TIMESTAMP, -1);



