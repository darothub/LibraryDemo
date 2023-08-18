CREATE TABLE IF NOT EXISTS `users`
(
    `id`   IDENTITY NOT NULL PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `books`
(
    `id`                IDENTITY NOT NULL PRIMARY KEY,
    `title`             TEXT NOT NULL,
    `author`            TEXT NOT NULL,
    `return_date`       DATE,
    `user_id`           INT,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
