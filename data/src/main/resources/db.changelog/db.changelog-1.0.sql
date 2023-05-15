--liquibase formatted sql

DROP TABLE IF EXISTS comments;

--changeset vlasevsky:1
CREATE TABLE comments (
                          comment_id SERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          post_id BIGINT NOT NULL,
                          parent_comment_id BIGINT DEFAULT NULL,
                          comment_text TEXT NOT NULL,
                          date_of_comment TIMESTAMP DEFAULT now(),
                          seen_status BOOLEAN DEFAULT FALSE

);

-- Удаление таблицы followers, если она существует
DROP TABLE IF EXISTS followers;


-- Создание таблицы followers
--changeset vlasevsky:2
CREATE TABLE followers (
                           id SERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL,
                           friend_id BIGINT NOT NULL,
                           seen_status BOOLEAN DEFAULT FALSE
);

-- Удаление таблицы likes, если она существует
DROP TABLE IF EXISTS likes;

-- Создание таблицы likes
--changeset vlasevsky:3
CREATE TABLE likes (
                       like_id SERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       post_id BIGINT NOT NULL,
                       date_of_like TIMESTAMP DEFAULT now(),
                       seen_status BOOLEAN DEFAULT FALSE
);

-- Удаление таблицы chats, если она существует
DROP TABLE IF EXISTS chats CASCADE;

-- Создание таблицы chats
--changeset vlasevsky:4
CREATE TABLE chats (
                       chat_id SERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       date_of_create TIMESTAMP DEFAULT now()
);


-- Удаление таблицы messages, если она существует
DROP TABLE IF EXISTS messages;

-- Создание таблицы messages
--changeset vlasevsky:5
CREATE TABLE messages (
                          message_id SERIAL PRIMARY KEY,
                          chat_id INT,
                          sender_id BIGINT NOT NULL,
                          recipient_id BIGINT NOT NULL,
                          message TEXT,
                          date_of_send TIMESTAMP DEFAULT now(),
                          seen_status BOOLEAN DEFAULT FALSE
);


-- Удаление таблицы posts, если она существует
DROP TABLE IF EXISTS posts CASCADE;

-- Создание таблицы posts
--changeset vlasevsky:6
CREATE TABLE posts (
                       post_id BIGINT PRIMARY KEY,
                       user_id BIGINT,
                       message TEXT,
                       content TEXT,
                       date_of_create TIMESTAMP DEFAULT now()
);

-- Удаление таблицы posts_seen, если она существует
DROP TABLE IF EXISTS posts_seen;

-- Создание таблицы posts_seen
--changeset vlasevsky:7
CREATE TABLE posts_seen (
                            seen_id SERIAL PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            post_id BIGINT NOT NULL
);

-- Удаление таблицы posts_statistics, если она существует
DROP TABLE IF EXISTS posts_statistics;

-- Создание таблицы posts_statistics
--changeset vlasevsky:8
CREATE TABLE posts_statistics (
                                  statistic_id SERIAL PRIMARY KEY,
                                  post_id BIGINT NOT NULL,
                                  like_count INTEGER DEFAULT 0,
                                  comment_count INTEGER DEFAULT 0
);

-- Удаление таблицы users, если она существует
DROP TABLE IF EXISTS users CASCADE;

-- Создание таблицы users
--changeset vlasevsky:9
CREATE TABLE users (
                       user_id BIGINT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       date_of_registry TIMESTAMP DEFAULT now(),
                       date_of_last_in DATE
);

-- Удаление таблицы profiles, если она существует
DROP TABLE IF EXISTS profiles;

-- Создание таблицы profiles
--changeset vlasevsky:10
CREATE TABLE profiles (
                          profile_id SERIAL PRIMARY KEY,
                          user_id BIGINT,
                          nickname VARCHAR(255) NOT NULL,
                          photo VARCHAR(255)
);

-- Создание внешних ключей
ALTER TABLE comments ADD CONSTRAINT FK_comments_posts FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE comments ADD CONSTRAINT FK_comments_comments FOREIGN KEY (parent_comment_id) REFERENCES comments (comment_id);
ALTER TABLE followers ADD CONSTRAINT FK_followers_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE followers ADD CONSTRAINT FK_followers_friends FOREIGN KEY (friend_id) REFERENCES users (user_id);
ALTER TABLE likes ADD CONSTRAINT FK_likes_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE likes ADD CONSTRAINT FK_likes_posts FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE messages ADD CONSTRAINT FK_messages_users_sender FOREIGN KEY (sender_id) REFERENCES users (user_id);
ALTER TABLE messages ADD CONSTRAINT FK_messages_users_recipient FOREIGN KEY (recipient_id) REFERENCES users (user_id);
ALTER TABLE messages ADD CONSTRAINT FK_messages_chat FOREIGN KEY (chat_id) REFERENCES  chats (chat_id);
ALTER TABLE chats ADD CONSTRAINT FK_chats_users FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE posts ADD CONSTRAINT FK_posts_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE posts_seen ADD CONSTRAINT FK_posts_seen_users FOREIGN KEY (user_id) REFERENCES users (user_id);
ALTER TABLE posts_seen ADD CONSTRAINT FK_posts_seen_posts FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE posts_statistics ADD CONSTRAINT FK_posts_statistics_posts FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE profiles ADD CONSTRAINT FK_profiles_users FOREIGN KEY (user_id) REFERENCES users (user_id);
