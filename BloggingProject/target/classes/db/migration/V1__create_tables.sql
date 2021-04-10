drop table if exists captcha_codes;
drop table if exists global_settings;
drop table if exists post_comments;
drop table if exists post_votes;
drop table if exists posts;
drop table if exists tags;
drop table if exists tags2posts;
drop table if exists users;
-- drop table if exists roles;

create table captcha_codes (id integer not null AUTO_INCREMENT, code varchar(255) not null, secret_code varchar(255) not null, time datetime(6) not null, primary key (id));
create table global_settings (id integer not null AUTO_INCREMENT, code varchar(255) not null, name varchar(255) not null, value varchar(255) not null, primary key (id));
create table post_comments (id integer not null AUTO_INCREMENT, text varchar(255) not null, time datetime(6) not null, parent_id integer, post_id integer not null, user_id integer not null, primary key (id));
create table post_votes (id integer not null AUTO_INCREMENT, time datetime(6) not null, value tinyint not null, post_id integer not null, user_id integer not null, primary key (id));
create table posts (id integer not null AUTO_INCREMENT, is_active tinyint not null, moderation_status enum('NEW','ACCEPTED','DECLINED') default 'NEW' not null, text varchar(255) not null, time datetime(6) not null, title varchar(255) not null, view_count integer not null, moderator_id integer, user_id integer not null, primary key (id));
create table tags (id integer not null AUTO_INCREMENT, name varchar(255) not null, primary key (id));
create table tags2posts (id integer not null AUTO_INCREMENT, post_id integer not null, tag_id integer not null, primary key (id));
create table users (id integer not null AUTO_INCREMENT, code varchar(255), email varchar(255) not null, is_moderator tinyint not null, name varchar(255) not null, password varchar(255) not null, photo varchar(255), reg_time datetime(6) not null, primary key (id));
-- create table roles (user_id integer not null AUTO_INCREMENT, roles varchar(255))