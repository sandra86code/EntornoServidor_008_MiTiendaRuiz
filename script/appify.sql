-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: mysqlDawes:3306
-- Tiempo de generación: 05-11-2022 a las 19:50:17
-- Versión del servidor: 5.7.22
-- Versión de PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appify`
--

-- --------------------------------------------------------

CREATE DATABASE appify;



--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `appify`.`users` (
  `nick` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `surname` varchar(60) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `birth_date` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `appify`.`users` (`nick`, `password`, `name`, `surname`, `birth_date`, `sex`, `admin`) VALUES
('acator5h', '4d00b383a488d947282f31e61dcf8681', 'Yú', 'Cator', '1936-02-18', 'M', 0),
('agillow4', '1af337078639f57eaada34e2bf692d39', 'Nadège', 'Gillow', '2003-04-13', 'H', 0),
('cchippindale5l', '5e49124f243a8f15636c75be0095d97c', 'Méng', 'Chippindale', '1982-01-06', 'H', 0),
('cmitchelson5g', '6b4fbb94f27db99327c998d77ca5ec99', 'Méghane', 'Mitchelson', '1923-09-01', 'M', 0),
('gaujean5n', 'e4df2dc12bcd0b41543374e221433cce', 'Styrbjörn', 'Aujean', '1976-12-22', 'H', 0),
('giannitti5p', 'd55e613aaa591989a4a1508bf7f0d372', 'Marie-françoise', 'Iannitti', '1924-01-15', 'M', 0),
('jmussetti0', '297e0131b6ec59967c5804c626955b9f', 'Aí', 'Mussetti', '1962-09-17', 'H', 0),
('mmonkton1', 'af40411194539dcc705931065e4054d8', 'Gwenaëlle', 'Monkton', '1968-06-07', 'M', 0),
('nlovelock3', '02ff53d6e4fb0610a5719704901a58c1', 'Maëlys', 'Lovelock', '1944-10-11', 'M', 0),
('tyurtsev5q', '5de5c739b9546ca2ef72cea7b898a942', 'Maëlle', 'Yurtsev', '1939-08-17', 'M', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `appify`.`category` (
  `cod` BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL UNIQUE,
  `description` varchar(240) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `appify`.`category` (`name`, `description`) VALUES
('Mobile', 'Nulla ac enim.'),
('Health Care', 'Nunc purus.'),
('SQL', 'Sed ante.'),
('Text Editor', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.'),
('Technology', 'Fusce posuere felis sed lacus.'),
('Navigator', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.'),
('Energy', 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.'),
('Public Utilities', 'Etiam vel augue.'),
('Consumer Durables', 'In congue.'),
('Bank', 'Integer tincidunt ante vel ipsum.'),
('Translator', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.'),
('Music', 'Fusce consequat.'),
('Entertainment', 'Quisque porta volutpat erat.'),
('Productivity', 'Etiam faucibus cursus urna.'),
('Capital Goods', 'Suspendisse accumsan tortor quis turpis.'),
('Game', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.'),
('Consumer Services', 'Donec semper sapien a libero.'),
('Social media', 'In est risus, auctor sed, tristique in, tempus sit amet, sem.'),
('Lifestyle', 'Integer non velit.'),
('Educational', 'In sagittis dui vel nisl.');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `article`
--

CREATE TABLE `appify`.`article` (
  `cod` BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL UNIQUE,
  `description` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `category_id` BIGINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `article`
--

INSERT INTO `appify`.`article` (`name`, `description`, `price`, `category_id`) VALUES
('Cardguard', 'In hac habitasse platea dictumst.', '229.31', 19),
('Microsoft Word', 'Nulla tellus.', '480.82', 17),
('Otcom', 'Quisque id justo sit amet sapien dignissim vestibulum.', '322.14', 5),
('Namfix', 'Proin eu mi.', '146.02', 16),
('Treeflex', 'Vestibulum sed magna at nunc commodo placerat.', '324.20', 10),
('Toughjoyfax', 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '65.78', 6),
('Bamity', 'Duis mattis egestas metus.', '361.78', 1),
('Rank', 'Duis bibendum.', '468.02', 11),
('Veribet', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '141.57', 12),
('Bitchip', 'Etiam justo.', '301.18', 8),
('Fix San', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '310.97', 1),
('Duobam', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.', '492.70', 2),
('Bitwolf', 'Proin at turpis a pede posuere nonummy.', '427.47', 9),
('Alpha', 'Etiam faucibus cursus urna.', '185.74', 4),
('Matsoft', 'Integer non velit.', '158.63', 2),
('Domainer', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', '488.29', 6),
('Flowdesk', 'Sed ante.', '288.75', 10),
('Fixy San', 'Suspendisse potenti.', '174.19', 11),
('Vagram', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '87.91', 20),
('Joyfax', 'Phasellus id sapien in sapien iaculis congue.', '433.67', 1),
('Span', 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula.', '43.87', 8),
('Sonsing', 'Duis at velit eu est congue elementum.', '108.85', 19),
('Bytecard', 'Integer ac leo.', '190.31', 8),
('Sonsingy', 'Aliquam erat volutpat.', '331.75', 9),
('Stronghold', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '66.13', 11),
('Cookley', 'Vivamus tortor.', '396.85', 7),
('Trippledex', 'In eleifend quam a odio.', '389.90', 10),
('Prodder', 'Mauris ullamcorper purus sit amet nulla.', '270.88', 3),
('Biodex', 'Sed sagittis.', '385.13', 8),
('Latlux', 'Pellentesque eget nunc.', '237.55', 6),
('Sub-Ex', 'Aliquam erat volutpat.', '459.52', 17),
('Cardify', 'In hac habitasse platea dictumst.', '222.86', 20),
('Ventosanzap', 'Nulla tempus.', '458.20', 3),
('Hatity', 'Pellentesque eget nunc.', '153.23', 11),
('Mat Lam Tam', 'Nam nulla.', '168.91', 13),
('Lrodder', 'Integer tincidunt ante vel ipsum.', '167.55', 10),
('Procadder', 'In hac habitasse platea dictumst.', '95.95', 1),
('Subin', 'In hac habitasse platea dictumst.', '299.24', 17),
('Y-Solowarm', 'In hac habitasse platea dictumst.', '189.79', 16),
('Wrapsafe', 'Curabitur convallis.', '190.31', 10),
('Spanco', 'Praesent lectus.', '233.47', 15),
('Mamfix', 'Integer non velit.', '271.55', 6),
('Y-find', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.', '261.71', 11),
('Zathin', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', '231.57', 2),
('Zontrax', 'Quisque id justo sit amet sapien dignissim vestibulum.', '480.51', 9),
('Carcottify', 'Donec dapibus.', '189.19', 3),
('Zaam-Dox', 'Nam tristique tortor eu pede.', '28.02', 19),
('Sonair', 'Praesent blandit lacinia erat.', '425.93', 5),
('Brapsafe', 'Nulla suscipit ligula in lacus.', '141.04', 19),
('Greenlam', 'Mauris lacinia sapien quis libero.', '438.03', 5),
('Tresom', 'In hac habitasse platea dictumst.', '184.65', 15),
('Stringtough', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', '352.64', 1),
('Rreeflex', 'Integer tincidunt ante vel ipsum.', '491.18', 5),
('Trodder', 'Maecenas tincidunt lacus at velit.', '461.48', 4),
('Xrapsafe', 'In hac habitasse platea dictumst.', '61.56', 17),
('Sardify', 'Nulla tempus.', '448.02', 13),
('Kanlam', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '129.42', 6),
('Opela', 'Suspendisse potenti.', '264.29', 10),
('Regrant', 'Cras in purus eu magna vulputate luctus.', '223.75', 12),
('Fintone', 'Proin interdum mauris non ligula pellentesque ultrices.', '236.61', 18),
('!onair', 'Aenean auctor gravida sem.', '317.29', 17),
('Wripplexex', 'In hac habitasse platea dictumst.', '329.02', 13),
('Andalax', 'Ut at dolor quis odio consequat varius.', '253.44', 16),
('Ronstring', 'Nulla facilisi.', '141.28', 7),
('Pannier', 'Aenean lectus.', '35.88', 15),
('Homing', 'Curabitur convallis.', '90.44', 8),
('Redhold', 'Nulla tellus.', '153.35', 5),
('Job', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.', '413.24', 15),
('Ponair', 'Morbi a ipsum.', '324.94', 11),
('Home Ing', 'Integer ac leo.', '386.17', 15),
('Mampflex', 'Nam nulla.', '93.32', 20),
('Yytecard', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '238.43', 10),
('Flowpesk', 'Aliquam erat volutpat.', '33.69', 4),
('Aerified', 'Integer ac neque.', '349.30', 6),
('Zonvrax', 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '491.49', 10),
('Bitolf', 'Cras in purus eu magna vulputate luctus.', '198.02', 15),
('Byteplax', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', '311.69', 16),
('Ventozap', 'Phasellus id sapien in sapien iaculis congue.', '433.78', 15),
('Subini', 'Vestibulum rutrum rutrum neque.', '259.66', 10),
('Zonair', 'Nunc nisl.', '346.79', 9),
('Bytewom', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', '438.05', 10),
('Zoolab', 'Praesent blandit lacinia erat.', '447.71', 13),
('Temp', 'Sed sagittis.', '115.77', 20),
('Supabin', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', '496.76', 15),
('Tin', 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', '105.23', 20),
('Tres-Zap', 'Suspendisse ornare consequat lectus.', '398.51', 20),
('Tampflex', 'Integer a nibh.', '253.94', 17),
('Jobiby', 'Fusce consequat.', '477.00', 6),
('Zathina', 'Nunc purus.', '445.93', 8),
('Wraccafe', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '270.32', 6),
('Flowmmesk', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', '280.26', 7),
('Cootley', 'Fusce consequat.', '407.48', 15),
('Asoka', 'Praesent blandit.', '137.94', 15),
('Voltsillam', 'Nulla ac enim.', '29.30', 13),
('Bitssalf', 'Duis consequat dui nec nisi volutpat eleifend.', '313.80', 13),
('X-Tolowarm', 'Integer a nibh.', '417.48', 9),
('Zamit', 'Vivamus vestibulum sagittis sapien.', '253.58', 20),
('Cank', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '269.15', 3),
('Trapsafe', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '345.81', 9),
('Spano', 'Sed vel enim sit amet nunc viverra dapibus.', '122.93', 14);

-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `appify`.`purchase` (
  `article_id` BIGINT NOT NULL,
  `user_nick` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `purchase_date` date NOT NULL,
  `article_price` decimal(5,2) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `purchase`
--

INSERT INTO `appify`.`purchase` (`article_id`, `user_nick`, `purchase_date`, `article_price`, `quantity`) VALUES
(41, 'agillow4', '2022-01-29','350.25',  2),
(66, 'gaujean5n', '2022-09-03', '475.77', 1),
(77, 'cmitchelson5g', '2022-04-20', '27.23', 4),
(2, 'mmonkton1', '2022-07-09', '180.49', 5),
(44, 'gaujean5n', '2022-06-18', '403.68', 1),
(87, 'nlovelock3', '2022-08-10', '305.42', 2),
(11, 'cmitchelson5g', '2022-03-11', '96.73', 3),
(2, 'agillow4', '2022-04-26', '458.31', 4),
(34, 'acator5h', '2021-11-29', '314.56', 6),
(32, 'jmussetti0', '2022-07-23', '90.71', 20),
(9, 'cchippindale5l', '2022-01-08', '142.07', 1),
(54, 'giannitti5p', '2022-04-24', '109.27', 2),
(14, 'acator5h', '2021-11-06', '20.50', 4),
(100, 'gaujean5n', '2021-12-12', '192.69', 1),
(29, 'agillow4', '2022-10-13', '107.34', 1),
(20, 'agillow4', '2022-01-27', '22.94', 5),
(58, 'gaujean5n', '2022-10-07', '287.42', 6),
(32, 'mmonkton1', '2022-08-14', '459.63', 7),
(12, 'mmonkton1', '2022-09-07', '468.78', 8),
(16, 'jmussetti0', '2022-10-09', '51.56', 9),
(96, 'giannitti5p', '2022-09-30', '436.78', 10),
(66, 'gaujean5n', '2022-01-30', '378.05', 1),
(70, 'cchippindale5l', '2021-12-11', '444.42', 20),
(46, 'giannitti5p', '2022-01-12', '195.59', 2),
(74, 'nlovelock3', '2022-05-10', '205.99', 1),
(25, 'gaujean5n', '2022-11-02', '321.73', 1),
(69, 'cchippindale5l', '2022-08-16', '358.23', 1),
(1, 'gaujean5n', '2022-06-21', '126.49', 4),
(88, 'agillow4', '2021-12-10', '205.01', 8),
(78, 'acator5h', '2022-08-10', '77.52', 7),
(6, 'giannitti5p', '2022-10-05', '283.63', 7),
(79, 'gaujean5n', '2022-09-08', '341.77', 50),
(88, 'agillow4', '2022-01-03', '342.95', 80),
(20, 'nlovelock3', '2022-01-06', '124.56', 11),
(61, 'acator5h', '2022-09-27', '289.72', 1),
(22, 'acator5h', '2022-09-14', '206.87', 1),
(10, 'cmitchelson5g', '2022-08-21', '79.83', 1),
(19, 'nlovelock3', '2022-05-03', '223.74', 4),
(23, 'agillow4', '2022-04-17', '9.78', 5),
(24, 'gaujean5n', '2021-11-20', '95.90', 1),
(17, 'jmussetti0', '2022-06-13', '417.42', 2),
(13, 'jmussetti0', '2022-03-14', '240.87', 2),
(20, 'cmitchelson5g', '2021-12-02', '53.77', 3),
(67, 'mmonkton1', '2022-03-17', '475.10', 6),
(79, 'gaujean5n', '2022-10-07', '125.58', 9),
(99, 'acator5h', '2022-09-21', '91.60', 12),
(24, 'mmonkton1', '2021-12-09', '298.07', 1),
(98, 'cmitchelson5g', '2022-07-15', '252.49', 3),
(29, 'acator5h', '2022-03-22', '302.32', 1),
(12, 'cchippindale5l', '2022-01-26', '303.26', 1),
(71, 'agillow4', '2022-06-20', '496.63', 2),
(25, 'agillow4', '2021-11-18', '222.00', 3),
(3, 'agillow4', '2022-10-22', '365.98', 1),
(16, 'gaujean5n', '2022-06-22', '255.24', 2),
(71, 'nlovelock3', '2022-08-05', '211.09', 3),
(41, 'nlovelock3', '2021-12-02', '184.88', 4),
(28, 'cchippindale5l', '2022-05-24', '300.19', 5),
(19, 'mmonkton1', '2022-04-13', '263.76', 1),
(39, 'cmitchelson5g', '2022-06-06', '115.02', 1),
(15, 'acator5h', '2021-12-14', '449.44', 1);

-- --------------------------------------------------------

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `users`
--
ALTER TABLE `appify`.`users`
  ADD PRIMARY KEY (`nick`);
  

--
-- Indices de la tabla `article`
--
ALTER TABLE `appify`.`article`
  ADD KEY `fk_article` (`category_id`);

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `appify`.`purchase`
  ADD PRIMARY KEY (`article_id`, `user_nick`, `purchase_date`),
  ADD KEY `fk_purchase_article` (`article_id`),
  ADD KEY `fk_purchase_user` (`user_nick`);


--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `article`
--
ALTER TABLE `appify`.`article`
  ADD CONSTRAINT `fk_article` FOREIGN KEY (`category_id`) REFERENCES `category` (`cod`) ON DELETE CASCADE;

--
-- Filtros para la tabla `purchase`
--
ALTER TABLE `appify`.`purchase`
  ADD CONSTRAINT `fk_purchase_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`cod`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_purchase_user` FOREIGN KEY (`user_nick`) REFERENCES `users` (`nick`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


CREATE USER 'ruiz'@'%' IDENTIFIED BY 'sandra';
GRANT ALL PRIVILEGES ON appify.* to 'ruiz'@'%';
