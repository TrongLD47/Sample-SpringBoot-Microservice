insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'eureka.client.register-with-eureka', 'true', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'eureka.client.fetch-registry', 'true', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'eureka.client.service-url.defaultZone', ' http://localhost:8761/eureka/', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'eureka.instance.hostname', 'localhost', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.datasource.url', 'jdbc:postgresql://localhost:7979/server_config_db', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.datasource.hikari.schema', 'server_config', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.datasource.username', 'postgres', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.datasource.password', '123456', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.jpa.properties.hibernate.dialect', 'org.hibernate.dialect.PostgreSQLDialect', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.jpa.properties.hibernate.default_schema', 'server_config', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.jpa.hibernate.ddl-auto', 'update', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.jpa.show-sql', 'false', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('CONFIG-SERVER', 'default', 'master', 'spring.jpa.open-in-view', 'false', null, now(), now());


insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.datasource.driver-class-name', 'org.postgresql.Driver', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.datasource.url', 'jdbc:postgresql://localhost:7979/server_config_db', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.datasource.hikari.schema', 'server_config', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.datasource.username', 'postgres', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.datasource.password', '123456', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.jpa.properties.hibernate.dialect', 'org.hibernate.dialect.PostgreSQLDialect', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.jpa.properties.hibernate.default_schema', 'server_config', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.jpa.hibernate.ddl-auto', 'validate', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.jpa.show-sql', 'false', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'spring.jpa.open-in-view', 'false', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'eureka.client.register-with-eureka', 'true', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'eureka.client.fetch-registry', 'true', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'eureka.client.service-url.defaultZone', ' http://localhost:8761/eureka/', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('DEPARTMENT-SERVICE', 'default', 'master', 'eureka.instance.hostname', 'localhost', null, now(), now());

insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'server.port', '9191', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].id', 'DEPARTMENT-SERVICE', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].uri', 'http://localhost:8001', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].predicates', 'Path=/departments/**', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].filters.name', 'CircuitBreaker', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].filters.args.name', 'DEPARTMENT-SERVICE', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[1].filters.args.fallbackuri', 'forward:/departmentServiceFallBack', null, now(), now());

insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].id', 'USER-SERVICE', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].uri', 'http://localhost:9002', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].predicates', 'Path=/users/**', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].filters.name', 'CircuitBreaker', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].filters.args.name', 'USER-SERVICE', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('API-GATEWAY', 'default', 'master', 'spring.cloud.gateway.routes[0].filters.args.fallbackuri', 'forward:/userServiceFallBack', null, now(), now());

insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('USER-SERVICE', 'default', 'master', 'server.port', '9002', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('USER-SERVICE', 'default', 'master', 'spring.application.name', 'USER-SERVICE', null, now(), now());
insert into properties (application, profile, "label", "key", value, description, createddate, updateddate) values ('USER-SERVICE', 'default', 'master', 'spring.zipkin.base-url', 'http://127.0.0.1:9411/', null, now(), now());

