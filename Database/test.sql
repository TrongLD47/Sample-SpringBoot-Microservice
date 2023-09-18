-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: us-cdbr-east-06.cleardb.net    Database: heroku_74ab49c9900fb12
-- ------------------------------------------------------
-- Server version	5.6.50-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `my_properties`
--

drop table IF EXISTS `my_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
create TABLE `my_properties`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `application` varchar(255) NOT NULL,
    `profile`     varchar(255) NOT NULL,
    `label`       varchar(255) NOT NULL,
    `my_key`      varchar(255) NOT NULL,
    `my_value`    varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_properties`
--

lock
TABLES `my_properties` WRITE;
/*!40000 ALTER TABLE `my_properties` DISABLE KEYS */;
insert into `my_properties`
VALUES (1, 'api-gateway', 'dev', 'master', 'server.port', '6969'),
       (2, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[0].id', 'product-service'),
       (3, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[0].uri', 'http://localhost:8082'),
       (4, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[0].predicates[0]', 'Path=/api/v1/products/**'),
       (5, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[1].id', 'customer-service'),
       (6, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[1].uri', 'http://localhost:8081'),
       (7, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[1].predicates[0]', 'Path=/api/v1/customers/**'),
       (8, 'customer-service', 'dev', 'master', 'server.port', '8081'),
       (9, 'customer-service', 'dev', 'master', 'spring.datasource.url',
        'jdbc:postgresql://ec2-34-235-31-124.compute-1.amazonaws.com:5432/d7jq5cka63d948'),
       (10, 'customer-service', 'dev', 'master', 'spring.datasource.username', 'gvmkggjvnriplm'),
       (11, 'customer-service', 'dev', 'master', 'spring.datasource.password',
        'c52211555053e5d9e27a28f94a0b681f2d7bcd0c107adcf3099a2a1d8806c6dc'),
       (12, 'customer-service', 'dev', 'master', 'spring.jpa.properties.hibernate.dialect',
        'org.hibernate.dialect.PostgreSQLDialect'),
       (13, 'customer-service', 'dev', 'master', 'spring.jpa.properties.hibernate.format_sql', 'true'),
       (14, 'customer-service', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'update'),
       (15, 'customer-service', 'dev', 'master', 'spring.jpa.show-sql', 'true'),
       (16, 'product-service', 'dev', 'master', 'server.port', '8082'),
       (17, 'product-service', 'dev', 'master', 'spring.datasource.url',
        'jdbc:postgresql://ec2-54-86-106-48.compute-1.amazonaws.com:5432/dos8r0t25gpq0'),
       (18, 'product-service', 'dev', 'master', 'spring.datasource.username', 'hlscpjzvdpuepw'),
       (19, 'product-service', 'dev', 'master', 'spring.datasource.password',
        'd6cc7db330b58f86c5959ce006a193be16aa4e5a25f6b25be8b666bf2251e15f'),
       (20, 'product-service', 'dev', 'master', 'spring.jpa.properties.hibernate.dialect',
        'org.hibernate.dialect.PostgreSQLDialect'),
       (21, 'product-service', 'dev', 'master', 'spring.jpa.properties.hibernate.format_sql', 'true'),
       (22, 'product-service', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'update'),
       (23, 'product-service', 'dev', 'master', 'spring.jpa.show-sql', 'true'),
       (24, 'product-service', 'dev', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver'),
       (25, 'customer-service', 'dev', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver'),
       (26, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[2].id', 'account-service'),
       (27, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[2].uri', 'http://localhost:8083'),
       (28, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[2].predicates[0]', 'Path=/api/v1/**'),
       (30, 'account-service', 'dev', 'master', 'server.port', '8083'),
       (31, 'account-service', 'dev', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver'),
       (32, 'account-service', 'dev', 'master', 'spring.datasource.url',
        'jdbc:postgresql://ec2-3-213-228-206.compute-1.amazonaws.com:5432/d5706q3reev0vh'),
       (33, 'account-service', 'dev', 'master', 'spring.datasource.username', 'vsubddvvxbcsaw'),
       (34, 'account-service', 'dev', 'master', 'spring.datasource.password',
        '349b8cf8f92343b7ecc7a834c19020092d707a4f6bdcbdc4fadaf02f473626cd'),
       (35, 'account-service', 'dev', 'master', 'spring.jpa.properties.hibernate.dialect',
        'org.hibernate.dialect.PostgreSQLDialect'),
       (36, 'account-service', 'dev', 'master', 'spring.jpa.properties.hibernate.format_sql', 'true'),
       (37, 'account-service', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'update'),
       (38, 'account-service', 'dev', 'master', 'spring.jpa.show-sql', 'true'),
       (39, 'order-service', 'dev', 'master', 'server.port', '8084'),
       (40, 'order-service', 'dev', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver'),
       (41, 'order-service', 'dev', 'master', 'spring.datasource.url',
        'jdbc:postgresql://ec2-34-235-31-124.compute-1.amazonaws.com:5432/d2hnjbl23fve3k'),
       (42, 'order-service', 'dev', 'master', 'spring.datasource.username', 'xadbzdhetljerr'),
       (43, 'order-service', 'dev', 'master', 'spring.datasource.password',
        '6febb20cce9bbf602e0598254581281bdc1f62583717699d326b3212c14e2316'),
       (44, 'order-service', 'dev', 'master', 'spring.jpa.properties.hibernate.dialect',
        'org.hibernate.dialect.PostgreSQLDialect'),
       (45, 'order-service', 'dev', 'master', 'spring.jpa.properties.hibernate.format_sql', 'true'),
       (46, 'order-service', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'update'),
       (47, 'order-service', 'dev', 'master', 'spring.jpa.show-sql', 'true'),
       (48, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[3].id', 'order-service'),
       (49, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[3].uri', 'http://localhost:8084'),
       (50, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[3].predicates[0]', 'Path=/api/v1/orders/**'),
       (51, 'auth-service', 'dev', 'master', 'server.port', '8085'),
       (52, 'auth-service', 'dev', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver'),
       (53, 'auth-service', 'dev', 'master', 'spring.datasource.url',
        'jdbc:postgresql://localhost:5432/AccountManagement'),
       (54, 'auth-service', 'dev', 'master', 'spring.datasource.username', 'postgres'),
       (55, 'auth-service', 'dev', 'master', 'spring.datasource.password', '5071'),
       (56, 'auth-service', 'dev', 'master', 'spring.jpa.properties.hibernate.dialect',
        'org.hibernate.dialect.PostgreSQLDialect'),
       (57, 'auth-service', 'dev', 'master', 'spring.jpa.properties.hibernate.format_sql', 'true'),
       (58, 'auth-service', 'dev', 'master', 'spring.jpa.hibernate.ddl-auto', 'update'),
       (59, 'auth-service', 'dev', 'master', 'spring.jpa.show-sql', 'true'),
       (60, 'auth-service', 'dev', 'master', 'security.jwt.uri', '/api/v1/auth/**'),
       (61, 'auth-service', 'dev', 'master', 'security.jwt.header', 'Authorization'),
       (62, 'auth-service', 'dev', 'master', 'security.jwt.prefix', 'Bearer '),
       (63, 'auth-service', 'dev', 'master', 'security.jwt.secret', 'anonymouse'),
       (64, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[4].id', 'auth-service'),
       (65, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[4].uri', 'http://localhost:8085'),
       (66, 'api-gateway', 'dev', 'master', 'spring.cloud.gateway.routes[4].predicates[0]', 'Path=/api/v1/auth/**'),
       (67, 'api-gateway', 'dev', 'master', 'security.jwt.uri', '/api/v1/auth/**'),
       (68, 'api-gateway', 'dev', 'master', 'security.jwt.header', 'Authorization'),
       (69, 'api-gateway', 'dev', 'master', 'security.jwt.prefix', 'Bearer '),
       (70, 'api-gateway', 'dev', 'master', 'security.jwt.secret', 'anonymouse'),
       (71, 'customer-service', 'dev', 'master', 'spring.redis.host', 'localhost'),
       (72, 'customer-service', 'dev', 'master', 'spring.redis.port', '6379'),
       (73, 'customer-service', 'dev', 'master', 'spring.cache.type', 'redis'),
       (74, 'customer-service', 'dev', 'master', 'spring.cache.redis.cache-null-value', 'false'),
       (75, 'product-service', 'dev', 'master', 'spring.redis.host', 'localhost'),
       (76, 'product-service', 'dev', 'master', 'spring.redis.port', '6379'),
       (77, 'product-service', 'dev', 'master', 'spring.cache.type', 'redis'),
       (78, 'product-service', 'dev', 'master', 'spring.cache.redis.cache-null-value', 'false'),
       (79, 'order-service', 'dev', 'master', 'spring.redis.host', 'localhost'),
       (80, 'order-service', 'dev', 'master', 'spring.redis.port', '6379'),
       (81, 'order-service', 'dev', 'master', 'spring.cache.type', 'redis'),
       (82, 'order-service', 'dev', 'master', 'spring.cache.redis.cache-null-value', 'false'),
       (83, 'auth-service', 'dev', 'master', 'spring.security.oauth2.client.registration.google.clientId',
        '898769202308-p45jdu9md8vdj246goh2t07242srm4h5.apps.googleusercontent.com'),
       (84, 'auth-service', 'dev', 'master', 'spring.security.oauth2.client.registration.google.clientSecret',
        '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'),
       (85, 'auth-service', 'dev', 'master', 'spring.security.oauth2.client.registration.google.scope', 'email'),
       (86, 'auth-service', 'dev', 'master', 'spring.security.oauth2.client.registration.google.scope', 'profile'),
       (94, 'account-service', 'dev', 'master', 'spring.redis.host', 'localhost'),
       (104, 'account-service', 'dev', 'master', 'spring.redis.port', '6379'),
       (114, 'account-service', 'dev', 'master', 'spring.cache.type', 'redis'),
       (124, 'account-service', 'dev', 'master', 'spring.cache.redis.cache-null-value', 'false');
/*!40000 ALTER TABLE `my_properties` ENABLE KEYS */;
UNLOCK
TABLES;
