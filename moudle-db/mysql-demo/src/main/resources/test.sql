CREATE TABLE `t2`
(
    `id` int NOT NULL AUTO_INCREMENT,
    `a`  int DEFAULT NULL,
    `b`  int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY  `idx_a` (`a`),
    KEY  `idx_b` (`b`)
) ENGINE=InnoDB;