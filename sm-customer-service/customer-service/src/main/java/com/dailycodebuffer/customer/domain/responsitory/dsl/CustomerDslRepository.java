package com.dailycodebuffer.customer.domain.responsitory.dsl;

import com.dailycodebuffer.common.common.Page;
import com.dailycodebuffer.common.common.SortDirection;
import com.dailycodebuffer.common.util.RequestUtils;
import com.dailycodebuffer.customer.domain.entity.CustomerEntity;
import com.dailycodebuffer.customer.domain.entity.QCustomerEntity;
import com.dailycodebuffer.customer.proto.ListCustomerRequest;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.querydsl.core.types.OrderSpecifier.NullHandling.NullsFirst;

@Repository
@RequiredArgsConstructor
public class CustomerDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QCustomerEntity customer = QCustomerEntity.customerEntity;


    public Page<CustomerEntity> getAllCustomer(ListCustomerRequest request) {
        int _page = RequestUtils.getPage(request.getPageRequest().getPage());
        int _size = RequestUtils.getSize(request.getPageRequest().getSize());
        int _offset = _page * _size;

        JPAQuery<CustomerEntity> query = jpaQueryFactory.select(customer)
                .from(customer);

        if (request.getClassId() > 0) {
            query.where(customer.userId.eq(request.getClassId()));
        }

        if (request.getFromAge() >= 0) {
            query.where(customer.age.loe(request.getFromAge()));
        }

        if (StringUtils.hasText(request.getFirstName())) {
            query.where(customer.firstName.containsIgnoreCase(request.getFirstName()));
        }

        if (StringUtils.hasText(request.getLastName())) {
            query.where(customer.lastName.containsIgnoreCase(request.getLastName()));
        }

        Order _order = StringUtils.hasText(request.getPageRequest().getDirection()) &&
                !SortDirection.isInvalid(request.getPageRequest().getDirection()) ?
                Order.valueOf(SortDirection.parse(request.getPageRequest().getDirection()).shortName.toUpperCase()) :
                Order.ASC;

        if (StringUtils.hasText(request.getPageRequest().getSort())) {
            if (request.getPageRequest().getSort().equalsIgnoreCase("firstName")) {
                query.orderBy(new OrderSpecifier<>(_order, customer.firstName, NullsFirst));
            } else if (request.getPageRequest().getSort().equalsIgnoreCase("lastName")){
                query.orderBy(new OrderSpecifier<>(_order, customer.lastName, NullsFirst));
            } else if (request.getPageRequest().getSort().equalsIgnoreCase("age")) {
                query.orderBy(new OrderSpecifier<>(_order, customer.age, NullsFirst));
            } else if (request.getPageRequest().getSort().equalsIgnoreCase("classId")){
                query.orderBy(new OrderSpecifier<>(_order, customer.userId, NullsFirst));
            } else {
                query.orderBy(new OrderSpecifier<>(_order, customer.firstName, NullsFirst));
            }
        } else {
            query.orderBy(new OrderSpecifier<>(_order, customer.lastName, NullsFirst));
        }

        JPAQuery<Long> count = query.clone().select(customer.id.countDistinct());
        if (!request.getAllInOne()){
            query.offset(_offset).limit(_size);
        }

        return new Page<>(query.fetch(), count.fetchFirst());
    }
}
