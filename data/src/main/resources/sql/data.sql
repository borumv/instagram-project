-- Удаление данных из таблиц
DELETE FROM posts_seen;
DELETE FROM posts_statistics;
DELETE FROM comments;
DELETE FROM likes;
DELETE FROM messages;
DELETE FROM followers;
DELETE FROM posts;
DELETE FROM profiles;
DELETE FROM users;

-- Заполнение таблицы users
INSERT INTO users (user_id, first_name, last_name, email, password, date_of_registry, date_of_last_in)
VALUES
    (1, 'John', 'Doe', 'john.doe@example.com', 'password123', '2022-01-01', '2022-05-01'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456', '2022-02-01', '2022-05-02'),
    (3, 'Bob', 'Johnson', 'bob.johnson@example.com', 'password789', '2022-03-01', '2022-05-03');

-- Заполнение таблицы profiles
INSERT INTO profiles (user_id, nickname, photo)
VALUES
    (1, 'johndoe', 'john.jpg'),
    (2, 'janesmith', 'jane.jpg'),
    (3, 'bobjohnson', 'bob.jpg');

-- Заполнение таблицы posts
INSERT INTO posts (post_id, user_id, message, date_of_create)
VALUES
    (1, 1, 'Hello, world!', '2022-05-01 10:00:00'),
    (2, 2, 'Nice day!', '2022-05-02 12:00:00'),
    (3, 3, 'I love SQL!', '2022-05-03 15:00:00');

-- Заполнение таблицы posts_statistics
INSERT INTO posts_statistics (statistic_id, post_id, like_count, comment_count)
VALUES
    (1, 1, 5, 3),
    (2, 2, 2, 1),
    (3, 3, 10, 5);

-- Заполнение таблицы comments
INSERT INTO comments (comment_id, user_id, post_id, parent_comment_id, comment_text, date_of_comment, seen_status)
VALUES
    (1, 2, 1, NULL, 'Great post!', '2022-05-01 11:00:00', TRUE),
    (2, 3, 1, 1, 'I agree!', '2022-05-01 12:00:00', FALSE),
    (3, 1, 2, NULL, 'Thanks!', '2022-05-02 13:00:00', TRUE),
    (4, 2, 3, NULL, 'SQL is awesome!', '2022-05-03 16:00:00', FALSE);

-- Заполнение таблицы likes
INSERT INTO likes (like_id, user_id, post_id, date_of_like, seen_status)
VALUES
    (1, 1, 2, '2022-05-02 14:00:00', TRUE),
    (2, 3, 3, '2022-05-03 17:00:00', FALSE);

-- Заполнение таблицы followers
INSERT INTO followers (id, user_id, friend_id, seen_status)
VALUES
    (1, 1, 2, TRUE),
    (2, 1, 3, FALSE),
    (3, 2, 1, TRUE),
    (4, 3, 2, FALSE);

INSERT INTO messages (message_id, sender_id, recipient_id, message, date_of_send, seen_status)
VALUES
    (1, 1, 2, 'Привет!', '2022-05-01 14:00:00', TRUE),
    (2, 2, 1, 'Привет! Как дела?', '2022-05-01 15:00:00', FALSE),
    (3, 1, 3, 'Привет, Боб!', '2022-05-02 16:00:00', TRUE),
    (4, 3, 1, 'Привет, Джон! Всё хорошо, спасибо!', '2022-05-02 17:00:00', FALSE),
    (5, 2, 3, 'Привет, Боб! Что нового?', '2022-05-03 18:00:00', TRUE),
    (6, 3, 2, 'Привет, Джейн! Ничего особенного, просто работаю.', '2022-05-03 19:00:00', FALSE);

-- Запросы на заполнение таблицы posts_seen
INSERT INTO posts_seen (seen_id, user_id, post_id)
VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3);
