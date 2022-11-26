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
('sandra86code', '36d0dbae93bab78f9ef80c9215fc7245 ', 'Sandra', 'Ruiz Jimenez', '1986-04-13', 'M', 1),
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
  `quantity` integer NOT NULL,
  `category_id` BIGINT NOT NULL,
  `image` LONGBLOB
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `article`
--

INSERT INTO `appify`.`article` (`name`, `description`, `price`, `quantity`, `category_id`, `image`) VALUES
('Cardguard', 'In hac habitasse platea dictumst.', '229.31', 2, 19, null),
('Microsoft Word', 'Nulla tellus.', '480.82', 3, 17, null),
('Otcom', 'Quisque id justo sit amet sapien dignissim vestibulum.', '322.14', 1, 5, null),
('Namfix', 'Proin eu mi.', '146.02', 100, 1, null),
('Treeflex', 'Vestibulum sed magna at nunc commodo placerat.', '324.20', 4, 10, null),
('Toughjoyfax', 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '65.78', 0, 6, null),
('Bamity', 'Duis mattis egestas metus.', '361.78', 20, 1, null),
('Rank', 'Duis bibendum.', '468.02', 50, 11, null),
('Veribet', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '141.57', 10, 12, null),
('Bitchip', 'Etiam justo.', '301.18', 3, 8, null),
('Fix San', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '310.97', 4, 1, null),
('Duobam', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.', '492.70', 5, 2, null),
('Bitwolf', 'Proin at turpis a pede posuere nonummy.', '427.47', 20, 9, null),
('Alpha', 'Etiam faucibus cursus urna.', '185.74', 30, 4, null),
('Matsoft', 'Integer non velit.', '158.63', 30, 2, null),
('Domainer', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', '488.29', 2, 6, null),
('Flowdesk', 'Sed ante.', '288.75', 0, 10, null),
('Fixy San', 'Suspendisse potenti.', '174.19', 2, 11, null),
('Vagram', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '87.91', 10, 20, null),
('Joyfax', 'Phasellus id sapien in sapien iaculis congue.', '433.67', 11, 1, null),
('Span', 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula.', '43.87', 5, 8, null),
('Sonsing', 'Duis at velit eu est congue elementum.', '108.85', 8, 19, null),
('Bytecard', 'Integer ac leo.', '190.31', 3, 8, null),
('Sonsingy', 'Aliquam erat volutpat.', '331.75', 15, 9, null),
('Stronghold', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '66.13', 20, 11, null);


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
(1, 'agillow4', '2022-01-29','350.25',  2),
(2, 'gaujean5n', '2022-09-03', '475.77', 1),
(3, 'cmitchelson5g', '2022-04-20', '27.23', 4),
(4, 'mmonkton1', '2022-07-09', '180.49', 5),
(5, 'gaujean5n', '2022-06-18', '403.68', 1),
(6, 'nlovelock3', '2022-08-10', '305.42', 2),
(7, 'cmitchelson5g', '2022-03-11', '96.73', 3),
(7, 'agillow4', '2022-04-26', '458.31', 4),
(9, 'acator5h', '2021-11-29', '314.56', 6),
(10, 'jmussetti0', '2022-07-23', '90.71', 20);

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
