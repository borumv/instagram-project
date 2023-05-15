--changeset vlasevsky:1
ALTER TABLE comments
    ADD CONSTRAINT FK_comments_users FOREIGN KEY (user_id) REFERENCES users (user_id);