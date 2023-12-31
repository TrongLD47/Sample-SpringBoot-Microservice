package com.dailycodebuffer.customer.domain.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaQueryDslConfig {

    @Bean
    public JPAQueryFactory jpaQueryBuilder(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
