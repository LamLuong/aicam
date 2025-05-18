PRAGMA foreign_keys = ON;

-- Table structure for table `clients`
CREATE TABLE IF NOT EXISTS `users` (
     `id` INTEGER PRIMARY KEY AUTOINCREMENT,
     `username` VARCHAR(255) NOT NULL,
     `email` VARCHAR(100) NOT NULL,
     `password` VARCHAR(255) NOT NULL
);

-- Table structure for table `employees`
CREATE TABLE IF NOT EXISTS `camera` (
    `id` INTEGER PRIMARY KEY AUTOINCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `user_id` INTEGER NOT NULL,
    `create_at` TIMESTAMP NOT NULL,

    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
