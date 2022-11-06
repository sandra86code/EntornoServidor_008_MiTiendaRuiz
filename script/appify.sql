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
  `cod` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL UNIQUE,
  `description` varchar(240) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `appify`.`category` (`cod`, `name`, `description`) VALUES
(1, 'Mobile', 'Nulla ac enim.'),
(2, 'Health Care', 'Nunc purus.'),
(3, 'SQL', 'Sed ante.'),
(4, 'Text Editor', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.'),
(5, 'Technology', 'Fusce posuere felis sed lacus.'),
(6, 'Navigator', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.'),
(7, 'Energy', 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.'),
(8, 'Public Utilities', 'Etiam vel augue.'),
(9, 'Consumer Durables', 'In congue.'),
(10, 'Bank', 'Integer tincidunt ante vel ipsum.'),
(11, 'Translator', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.'),
(12, 'Music', 'Fusce consequat.'),
(13, 'Entertainment', 'Quisque porta volutpat erat.'),
(14, 'Productivity', 'Etiam faucibus cursus urna.'),
(15, 'Capital Goods', 'Suspendisse accumsan tortor quis turpis.'),
(16, 'Game', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.'),
(17, 'Consumer Services', 'Donec semper sapien a libero.'),
(18, 'Social media', 'In est risus, auctor sed, tristique in, tempus sit amet, sem.'),
(19, 'Lifestyle', 'Integer non velit.'),
(20, 'Educational', 'In sagittis dui vel nisl.');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `article`
--

CREATE TABLE `appify`.`article` (
  `cod` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL UNIQUE,
  `description` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `article`
--

INSERT INTO `appify`.`article` (`cod`, `name`, `description`, `price`, `category_id`) VALUES
(1, 'Cardguard', 'In hac habitasse platea dictumst.', '229.31', 19),
(2, 'Microsoft Word', 'Nulla tellus.', '480.82', 17),
(3, 'Otcom', 'Quisque id justo sit amet sapien dignissim vestibulum.', '322.14', 5),
(4, 'Namfix', 'Proin eu mi.', '146.02', 16),
(5, 'Treeflex', 'Vestibulum sed magna at nunc commodo placerat.', '324.20', 10),
(6, 'Toughjoyfax', 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', '65.78', 6),
(7, 'Bamity', 'Duis mattis egestas metus.', '361.78', 1),
(8, 'Rank', 'Duis bibendum.', '468.02', 11),
(9, 'Veribet', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '141.57', 12),
(10, 'Bitchip', 'Etiam justo.', '301.18', 8),
(11, 'Fix San', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '310.97', 1),
(12, 'Duobam', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae.', '492.70', 2),
(13, 'Bitwolf', 'Proin at turpis a pede posuere nonummy.', '427.47', 9),
(14, 'Alpha', 'Etiam faucibus cursus urna.', '185.74', 4),
(15, 'Matsoft', 'Integer non velit.', '158.63', 2),
(16, 'Domainer', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', '488.29', 6),
(17, 'Flowdesk', 'Sed ante.', '288.75', 10),
(18, 'Fixy San', 'Suspendisse potenti.', '174.19', 11),
(19, 'Vagram', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', '87.91', 20),
(20, 'Joyfax', 'Phasellus id sapien in sapien iaculis congue.', '433.67', 1),
(21, 'Span', 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula.', '43.87', 8),
(22, 'Sonsing', 'Duis at velit eu est congue elementum.', '108.85', 19),
(23, 'Bytecard', 'Integer ac leo.', '190.31', 8),
(24, 'Sonsingy', 'Aliquam erat volutpat.', '331.75', 9),
(25, 'Stronghold', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '66.13', 11),
(26, 'Cookley', 'Vivamus tortor.', '396.85', 7),
(27, 'Trippledex', 'In eleifend quam a odio.', '389.90', 10),
(28, 'Prodder', 'Mauris ullamcorper purus sit amet nulla.', '270.88', 3),
(29, 'Biodex', 'Sed sagittis.', '385.13', 8),
(30, 'Latlux', 'Pellentesque eget nunc.', '237.55', 6),
(31, 'Sub-Ex', 'Aliquam erat volutpat.', '459.52', 17),
(32, 'Cardify', 'In hac habitasse platea dictumst.', '222.86', 20),
(33, 'Ventosanzap', 'Nulla tempus.', '458.20', 3),
(34, 'Hatity', 'Pellentesque eget nunc.', '153.23', 11),
(35, 'Mat Lam Tam', 'Nam nulla.', '168.91', 13),
(36, 'Lrodder', 'Integer tincidunt ante vel ipsum.', '167.55', 10),
(37, 'Procadder', 'In hac habitasse platea dictumst.', '95.95', 1),
(38, 'Subin', 'In hac habitasse platea dictumst.', '299.24', 17),
(39, 'Y-Solowarm', 'In hac habitasse platea dictumst.', '189.79', 16),
(40, 'Wrapsafe', 'Curabitur convallis.', '190.31', 10),
(41, 'Spanco', 'Praesent lectus.', '233.47', 15),
(42, 'Mamfix', 'Integer non velit.', '271.55', 6),
(43, 'Y-find', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.', '261.71', 11),
(44, 'Zathin', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', '231.57', 2),
(45, 'Zontrax', 'Quisque id justo sit amet sapien dignissim vestibulum.', '480.51', 9),
(46, 'Carcottify', 'Donec dapibus.', '189.19', 3),
(47, 'Zaam-Dox', 'Nam tristique tortor eu pede.', '28.02', 19),
(48, 'Sonair', 'Praesent blandit lacinia erat.', '425.93', 5),
(49, 'Brapsafe', 'Nulla suscipit ligula in lacus.', '141.04', 19),
(50, 'Greenlam', 'Mauris lacinia sapien quis libero.', '438.03', 5),
(51, 'Tresom', 'In hac habitasse platea dictumst.', '184.65', 15),
(52, 'Stringtough', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', '352.64', 1),
(53, 'Rreeflex', 'Integer tincidunt ante vel ipsum.', '491.18', 5),
(54, 'Trodder', 'Maecenas tincidunt lacus at velit.', '461.48', 4),
(55, 'Xrapsafe', 'In hac habitasse platea dictumst.', '61.56', 17),
(56, 'Sardify', 'Nulla tempus.', '448.02', 13),
(57, 'Kanlam', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', '129.42', 6),
(58, 'Opela', 'Suspendisse potenti.', '264.29', 10),
(59, 'Regrant', 'Cras in purus eu magna vulputate luctus.', '223.75', 12),
(60, 'Fintone', 'Proin interdum mauris non ligula pellentesque ultrices.', '236.61', 18),
(61, '!onair', 'Aenean auctor gravida sem.', '317.29', 17),
(62, 'Wripplexex', 'In hac habitasse platea dictumst.', '329.02', 13),
(63, 'Andalax', 'Ut at dolor quis odio consequat varius.', '253.44', 16),
(64, 'Ronstring', 'Nulla facilisi.', '141.28', 7),
(65, 'Pannier', 'Aenean lectus.', '35.88', 15),
(66, 'Homing', 'Curabitur convallis.', '90.44', 8),
(67, 'Redhold', 'Nulla tellus.', '153.35', 5),
(68, 'Job', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.', '413.24', 15),
(69, 'Ponair', 'Morbi a ipsum.', '324.94', 11),
(70, 'Home Ing', 'Integer ac leo.', '386.17', 15),
(71, 'Mampflex', 'Nam nulla.', '93.32', 20),
(72, 'Yytecard', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '238.43', 10),
(73, 'Flowpesk', 'Aliquam erat volutpat.', '33.69', 4),
(74, 'Aerified', 'Integer ac neque.', '349.30', 6),
(75, 'Zonvrax', 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '491.49', 10),
(76, 'Bitolf', 'Cras in purus eu magna vulputate luctus.', '198.02', 15),
(77, 'Byteplax', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', '311.69', 16),
(78, 'Ventozap', 'Phasellus id sapien in sapien iaculis congue.', '433.78', 15),
(79, 'Subini', 'Vestibulum rutrum rutrum neque.', '259.66', 10),
(80, 'Zonair', 'Nunc nisl.', '346.79', 9),
(81, 'Bytewom', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', '438.05', 10),
(82, 'Zoolab', 'Praesent blandit lacinia erat.', '447.71', 13),
(83, 'Temp', 'Sed sagittis.', '115.77', 20),
(84, 'Supabin', 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', '496.76', 15),
(85, 'Tin', 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', '105.23', 20),
(86, 'Tres-Zap', 'Suspendisse ornare consequat lectus.', '398.51', 20),
(87, 'Tampflex', 'Integer a nibh.', '253.94', 17),
(88, 'Jobiby', 'Fusce consequat.', '477.00', 6),
(89, 'Zathina', 'Nunc purus.', '445.93', 8),
(90, 'Wraccafe', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '270.32', 6),
(91, 'Flowmmesk', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', '280.26', 7),
(92, 'Cootley', 'Fusce consequat.', '407.48', 15),
(93, 'Asoka', 'Praesent blandit.', '137.94', 15),
(94, 'Voltsillam', 'Nulla ac enim.', '29.30', 13),
(95, 'Bitssalf', 'Duis consequat dui nec nisi volutpat eleifend.', '313.80', 13),
(96, 'X-Tolowarm', 'Integer a nibh.', '417.48', 9),
(97, 'Zamit', 'Vivamus vestibulum sagittis sapien.', '253.58', 20),
(98, 'Cank', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '269.15', 3),
(99, 'Trapsafe', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', '345.81', 9),
(100, 'Spano', 'Sed vel enim sit amet nunc viverra dapibus.', '122.93', 14);

-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `appify`.`purchase` (
  `cod` int(11) NOT NULL,
  `article_price` decimal(5,2) NOT NULL,
  `article_id` int(11) NOT NULL,
  `user_nick` varchar(30) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `purchase_date` date NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `purchase`
--

INSERT INTO `appify`.`purchase` (`cod`, `article_price`, `article_id`, `user_nick`, `purchase_date`, `quantity`) VALUES
(1, '350.25', 41, 'agillow4', '2022-01-29', 2),
(2, '475.77', 66, 'gaujean5n', '2022-09-03', 1),
(3, '27.23', 77, 'cmitchelson5g', '2022-04-20', 4),
(4, '180.49', 2, 'mmonkton1', '2022-07-09', 5),
(5, '403.68', 44, 'gaujean5n', '2022-06-18', 1),
(6, '305.42', 87, 'nlovelock3', '2022-08-10', 2),
(7, '96.73', 11, 'cmitchelson5g', '2022-03-11', 3),
(8, '458.31', 2, 'agillow4', '2022-04-26', 4),
(9, '314.56', 34, 'acator5h', '2021-11-29', 6),
(10, '90.71', 32, 'jmussetti0', '2022-07-23', 20),
(11, '142.07', 9, 'cchippindale5l', '2022-01-08', 1),
(12, '109.27', 54, 'giannitti5p', '2022-04-24', 2),
(13, '20.50', 14, 'acator5h', '2021-11-06', 4),
(14, '192.69', 100, 'gaujean5n', '2021-12-12', 1),
(15, '107.34', 29, 'agillow4', '2022-10-13', 1),
(16, '22.94', 20, 'agillow4', '2022-01-27', 5),
(17, '287.42', 58, 'gaujean5n', '2022-10-07', 6),
(18, '459.63', 32, 'mmonkton1', '2022-08-14', 7),
(19, '468.78', 12, 'mmonkton1', '2022-09-07', 8),
(20, '51.56', 16, 'jmussetti0', '2022-10-09', 9),
(21, '436.78', 96, 'giannitti5p', '2022-09-30', 10),
(22, '378.05', 66, 'gaujean5n', '2022-01-30', 1),
(23, '444.42', 70, 'cchippindale5l', '2021-12-11', 20),
(24, '195.59', 46, 'giannitti5p', '2022-01-12', 2),
(25, '205.99', 74, 'nlovelock3', '2022-05-10', 1),
(26, '321.73', 25, 'gaujean5n', '2022-11-02', 1),
(27, '358.23', 69, 'cchippindale5l', '2022-08-16', 1),
(28, '126.49', 1, 'gaujean5n', '2022-06-21', 4),
(29, '205.01', 88, 'agillow4', '2021-12-10', 8),
(30, '77.52', 78, 'acator5h', '2022-08-10', 7),
(31, '283.63', 6, 'giannitti5p', '2022-10-05', 7),
(32, '341.77', 79, 'gaujean5n', '2022-09-08', 50),
(33, '342.95', 88, 'agillow4', '2022-01-03', 80),
(34, '124.56', 20, 'nlovelock3', '2022-01-06', 11),
(35, '289.72', 61, 'acator5h', '2022-09-27', 1),
(36, '206.87', 22, 'acator5h', '2022-09-14', 1),
(37, '79.83', 10, 'cmitchelson5g', '2022-08-21', 1),
(38, '223.74', 19, 'nlovelock3', '2022-05-03', 4),
(39, '9.78', 23, 'agillow4', '2022-04-17', 5),
(40, '95.90', 24, 'gaujean5n', '2021-11-20', 1),
(41, '417.42', 17, 'jmussetti0', '2022-06-13', 2),
(42, '240.87', 13, 'jmussetti0', '2022-03-14', 2),
(43, '53.77', 20, 'cmitchelson5g', '2021-12-02', 3),
(44, '475.10', 67, 'mmonkton1', '2022-03-17', 6),
(45, '125.58', 79, 'gaujean5n', '2022-10-07', 9),
(46, '91.60', 99, 'acator5h', '2022-09-21', 12),
(47, '298.07', 24, 'mmonkton1', '2021-12-09', 1),
(48, '252.49', 98, 'cmitchelson5g', '2022-07-15', 3),
(49, '302.32', 29, 'acator5h', '2022-03-22', 1),
(50, '303.26', 12, 'cchippindale5l', '2022-01-26', 1),
(51, '496.63', 71, 'agillow4', '2022-06-20', 2),
(52, '222.00', 25, 'agillow4', '2021-11-18', 3),
(53, '365.98', 3, 'agillow4', '2022-10-22', 1),
(54, '255.24', 16, 'gaujean5n', '2022-06-22', 2),
(55, '211.09', 71, 'nlovelock3', '2022-08-05', 3),
(56, '184.88', 41, 'nlovelock3', '2021-12-02', 4),
(57, '300.19', 28, 'cchippindale5l', '2022-05-24', 5),
(58, '263.76', 19, 'mmonkton1', '2022-04-13', 1),
(59, '115.02', 39, 'cmitchelson5g', '2022-06-06', 1),
(60, '449.44', 15, 'acator5h', '2021-12-14', 1),
(61, '194.75', 40, 'jmussetti0', '2021-12-06', 1),
(62, '271.54', 30, 'cchippindale5l', '2021-12-03', 1),
(63, '389.84', 35, 'mmonkton1', '2022-03-19', 1),
(64, '454.85', 23, 'agillow4', '2022-04-24', 1),
(65, '279.33', 9, 'cmitchelson5g', '2022-01-10', 12),
(66, '27.94', 8, 'jmussetti0', '2022-06-23', 18),
(67, '340.27', 56, 'cchippindale5l', '2022-09-24', 1),
(68, '246.26', 38, 'gaujean5n', '2022-01-19', 1),
(69, '128.96', 9, 'nlovelock3', '2022-10-17', 1),
(70, '404.14', 71, 'acator5h', '2022-02-04', 1),
(71, '395.54', 13, 'cmitchelson5g', '2022-01-05', 1),
(72, '108.56', 40, 'agillow4', '2022-09-08', 1),
(73, '191.75', 50, 'acator5h', '2022-02-28', 15),
(74, '488.91', 16, 'giannitti5p', '2022-04-03', 1),
(75, '240.15', 43, 'cmitchelson5g', '2021-12-31', 1),
(76, '67.07', 51, 'jmussetti0', '2022-07-07', 1),
(77, '480.84', 25, 'cchippindale5l', '2021-12-19', 1),
(78, '370.83', 82, 'agillow4', '2022-04-30', 1),
(79, '225.92', 81, 'mmonkton1', '2022-02-09', 14),
(80, '467.55', 83, 'nlovelock3', '2022-09-16', 120),
(81, '152.87', 88, 'cchippindale5l', '2022-04-30', 1),
(82, '377.15', 33, 'cmitchelson5g', '2022-05-05', 1),
(83, '50.55', 14, 'jmussetti0', '2022-08-10', 1),
(84, '155.30', 27, 'cmitchelson5g', '2022-08-16', 1),
(85, '390.45', 46, 'jmussetti0', '2021-11-16', 1),
(86, '151.49', 67, 'mmonkton1', '2022-03-22', 6),
(87, '436.04', 79, 'agillow4', '2022-05-05', 9);

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
-- Indices de la tabla `category`
--
ALTER TABLE `appify`.`category`
  ADD PRIMARY KEY (`cod`);
  
--
-- Indices de la tabla `article`
--
ALTER TABLE `appify`.`article`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `fk_article` (`category_id`);

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `appify`.`purchase`
  ADD PRIMARY KEY (`cod`),
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
