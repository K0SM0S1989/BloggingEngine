drop table if exists captcha_codes;
drop table if exists global_settings;
drop table if exists post_comments;
drop table if exists post_votes;
drop table if exists posts;
drop table if exists tags;
drop table if exists tags2posts;
drop table if exists users;



create table captcha_codes (id SERIAL, code varchar(255) not null, secret_code varchar(255) not null, time timestamp not null, primary key (id));
create table global_settings (id SERIAL, code varchar(255) not null, name varchar(255) not null, value varchar(255) not null, primary key (id));
create table post_comments (id SERIAL, text varchar(255) not null, time timestamp not null, parent_id integer, post_id integer not null, user_id integer not null, primary key (id));
create table post_votes (id SERIAL, time timestamp not null, value int not null, post_id integer not null, user_id integer not null, primary key (id));
create table posts (id SERIAL, is_active int not null, moderation_status varchar(255) default 'NEW' not null, text varchar(1500) not null, time timestamp not null, title varchar(50) not null, view_count integer not null, moderator_id integer, user_id integer not null, primary key (id));
create table tags (id SERIAL, name varchar(255) not null, primary key (id));
create table tags2posts (id SERIAL, post_id integer not null, tag_id integer not null, primary key (id));
create table users (id SERIAL, code varchar(255), email varchar(100) not null, is_moderator int not null, name varchar(255) not null, password varchar(255) not null, photo varchar(255), reg_time timestamp not null, primary key (id));
