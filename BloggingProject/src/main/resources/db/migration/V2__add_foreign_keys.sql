alter table post_comments add constraint connectionComToParentCom foreign key (parent_id) references post_comments (id);
alter table post_comments add constraint connectionComToPost foreign key (post_id) references posts (id);
alter table post_comments add constraint connectionComToUser foreign key (user_id) references users (id);
alter table post_votes add constraint connectionVotesToPost foreign key (post_id) references posts (id);
alter table post_votes add constraint connectionVotesToUser foreign key (user_id) references users (id);
alter table posts add constraint connectionPostToModUser foreign key (moderator_id) references users (id);
alter table posts add constraint connectionPostToUser foreign key (user_id) references users (id);
alter table tags2posts add constraint connectionTagToPost foreign key (post_id) references posts (id);
alter table tags2posts add constraint connectionTagToTag foreign key (tag_id) references tags (id);
-- alter table roles add constraint connectionUserToRole foreign key (user_id) references users (id)