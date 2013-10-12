-- --------------------------------------------------------
-- Сервер:                       127.0.0.1
-- Server version:               5.6.12-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Версія:              8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table social_network_bionic.message_status_dictionary
CREATE TABLE IF NOT EXISTS `message_status_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Message_status_description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.message_status_dictionary: ~2 rows (approximately)
DELETE FROM `message_status_dictionary`;
/*!40000 ALTER TABLE `message_status_dictionary` DISABLE KEYS */;
INSERT INTO `message_status_dictionary` (`id`, `description`) VALUES
	(1, 'read'),
	(2, 'unread');
/*!40000 ALTER TABLE `message_status_dictionary` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.relations_dictionary
CREATE TABLE IF NOT EXISTS `relations_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Description_UNIQUE` (`Description`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.relations_dictionary: ~2 rows (approximately)
DELETE FROM `relations_dictionary`;
/*!40000 ALTER TABLE `relations_dictionary` DISABLE KEYS */;
INSERT INTO `relations_dictionary` (`id`, `Description`) VALUES
	(1, 'friends');
/*!40000 ALTER TABLE `relations_dictionary` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.request_status_dictionary
CREATE TABLE IF NOT EXISTS `request_status_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Request_status_description_UNIQUE` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.request_status_dictionary: ~2 rows (approximately)
DELETE FROM `request_status_dictionary`;
/*!40000 ALTER TABLE `request_status_dictionary` DISABLE KEYS */;
INSERT INTO `request_status_dictionary` (`id`, `description`) VALUES
	(3, 'blocked'),
	(1, 'confirmed'),
	(8, 'unconfirmed');
/*!40000 ALTER TABLE `request_status_dictionary` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.role_dictionary
CREATE TABLE IF NOT EXISTS `role_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Description_UNIQUE` (`Description`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.role_dictionary: ~2 rows (approximately)
DELETE FROM `role_dictionary`;
/*!40000 ALTER TABLE `role_dictionary` DISABLE KEYS */;
INSERT INTO `role_dictionary` (`id`, `Description`) VALUES
	(2, 'admin'),
	(1, 'user');
/*!40000 ALTER TABLE `role_dictionary` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `City` varchar(45) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `e-mail` varchar(45) NOT NULL,
  `password` varchar(15) NOT NULL,
  `photo` blob,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `e-mail_UNIQUE` (`e-mail`),
  KEY `idRole_dictionary_idx` (`role`),
  CONSTRAINT `idRole_dictionary` FOREIGN KEY (`role`) REFERENCES `role_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.users: ~6 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `Name`, `Surname`, `City`, `enable`, `e-mail`, `password`, `photo`, `role`) VALUES
	(2, 'Katerina', 'Gluschenko', 'Kharkiv', 1, 'mail@gmail.com', '1234', NULL, 2),
	(3, 'Olga', 'Petrova', 'Kherson', 1, 'olga@mail.com', '111', NULL, 2),
	(4, 'Ivan', 'Ivanov', 'Odessa', 1, 'ivanov@gmail.com', 'qwe', NULL, 2),
	(5, 'Ivan', 'Petrov', 'Kiev', 0, 'petrov@mail.com', '123', NULL, 1),
	(7, 'Marry', 'Surname', 'Kiev', 1, 'marry@mail.com', '123', NULL, 1),
	(8, 'oleg', 'oleg', 'city', 1, 'oleg@mail.com', '123', NULL, 1),
	(9, 'petr', 'petrov', 'petersburg', 1, 'petr@mail.com', '123', NULL, 1),
	(10, 'New', 'User', 'Test', 1, 'new@mail.com', '123', NULL, 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;


-- Dumping structure for table social_network_bionic.friends
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_first_user` int(11) NOT NULL,
  `id_second_user` int(11) NOT NULL,
  `Relations` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id_first_user`),
  KEY `_idx` (`id_second_user`),
  KEY `Relations_idx` (`Relations`),
  CONSTRAINT `idFirstUser` FOREIGN KEY (`id_first_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idSecondUser` FOREIGN KEY (`id_second_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Relations` FOREIGN KEY (`Relations`) REFERENCES `relations_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.friends: ~9 rows (approximately)
DELETE FROM `friends`;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` (`id`, `id_first_user`, `id_second_user`, `Relations`) VALUES
	(1, 2, 3, 1),
	(2, 3, 4, 1),
	(3, 2, 4, 1),
	(4, 3, 5, 1),
	(5, 2, 5, 1),
	(6, 9, 5, 1),
	(7, 7, 5, 1),
	(8, 2, 7, 1),
	(9, 2, 9, 1),
	(10, 3, 9, 1),
	(11, 9, 7, 1),
	(12, 2, 8, 1);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.friends_request
CREATE TABLE IF NOT EXISTS `friends_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_requesting` int(11) NOT NULL,
  `id_user_confirming` int(11) NOT NULL,
  `request_status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUserRequesting_idx` (`id_user_requesting`),
  KEY `idUserConfirming_idx` (`id_user_confirming`),
  KEY `request_status_idx` (`request_status`),
  CONSTRAINT `idUserConfirming` FOREIGN KEY (`id_user_confirming`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUserRequesting` FOREIGN KEY (`id_user_requesting`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `request_status` FOREIGN KEY (`request_status`) REFERENCES `request_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.friends_request: ~11 rows (approximately)
DELETE FROM `friends_request`;
/*!40000 ALTER TABLE `friends_request` DISABLE KEYS */;
INSERT INTO `friends_request` (`id`, `id_user_requesting`, `id_user_confirming`, `request_status`) VALUES
	(1, 3, 4, 1),
	(3, 2, 4, 1),
	(4, 2, 3, 1),
	(8, 3, 5, 1),
	(13, 2, 5, 1),
	(14, 9, 5, 1),
	(15, 2, 9, 1),
	(16, 7, 5, 1),
	(17, 2, 7, 1),
	(18, 3, 9, 1),
	(19, 9, 7, 1),
	(20, 2, 3, 1),
	(21, 2, 8, 1);
/*!40000 ALTER TABLE `friends_request` ENABLE KEYS */;


-- Dumping structure for table social_network_bionic.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user_sender` int(11) NOT NULL,
  `id_user_reciever` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `message_status` int(11) NOT NULL,
  `text_message` text,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id_user_sender`),
  KEY `id_idx1` (`id_user_reciever`),
  KEY `id_idx2` (`message_status`),
  CONSTRAINT `id_user_reciever` FOREIGN KEY (`id_user_reciever`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_user_sender` FOREIGN KEY (`id_user_sender`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `message_status` FOREIGN KEY (`message_status`) REFERENCES `message_status_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table social_network_bionic.messages: ~9 rows (approximately)
DELETE FROM `messages`;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`id`, `id_user_sender`, `id_user_reciever`, `date`, `time`, `message_status`, `text_message`) VALUES
	(10, 2, 3, '2013-07-28', '23:44:24', 1, 'dfdf'),
	(11, 3, 2, '2013-07-29', '00:31:01', 1, 'new text msg'),
	(19, 2, 3, '2013-07-29', '02:43:09', 1, NULL),
	(20, 3, 2, '2013-07-29', '02:48:30', 1, 'test'),
	(27, 2, 3, '2013-07-29', '03:30:56', 1, 'hello olga'),
	(28, 2, 3, '2013-07-29', '17:32:16', 1, 'hi olga'),
	(29, 8, 2, '2013-07-29', '17:34:01', 1, 'message'),
	(30, 3, 2, '2013-07-29', '17:41:21', 1, '123 send'),
	(31, 3, 4, '2013-07-29', '18:04:25', 1, 'Hello Ivan');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;


