insert into posts (is_active, moderation_status, text, time, title, view_count, moderator_id, user_id)
values (1, 'ACCEPTED',
'Текст четвёртого поста. Много слов бла бла бла, чтобы в анонс всё не поместилось',
 CURRENT_TIMESTAMP, 'Заголовок четвёртого поста',
2, 3, 1);

insert into posts (is_active, moderation_status, text, time, title, view_count, moderator_id, user_id)
values (1, 'ACCEPTED',
'Текст пятого поста надо тоже удлиннить, чтобы в анонс всё не поместилось',
 CURRENT_TIMESTAMP, 'Заголовок пятого поста',
2, 1, 4);

insert into posts (is_active, moderation_status, text, time, title, view_count, moderator_id, user_id)
values (1, 'DECLINED',
'Текст шестого поста буду заливать водой, а то в анонс влезет весь текст и не интересно будет открывать пост',
 CURRENT_TIMESTAMP, 'Заголовок шестого поста',
2, 1, 1);

insert into post_comments (text, time, post_id, user_id)
values ('Комент просто разнос', CURRENT_TIMESTAMP,  4, 2);

insert into post_comments (text, time, post_id, user_id)
values ('Комент просто разнос', CURRENT_TIMESTAMP,  4, 1);

insert into post_comments (text, time, post_id, user_id)
values ('Комент просто разнос', CURRENT_TIMESTAMP,  3, 2);

insert into post_votes (user_id, post_id, time, value)
values (1, 5, CURRENT_TIMESTAMP, 1);

insert into post_votes (user_id, post_id, time, value)
values (2, 5, CURRENT_TIMESTAMP, 1);

insert into post_votes (user_id, post_id, time, value)
values (2, 3, CURRENT_TIMESTAMP, 1);


