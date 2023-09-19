package com.dailycodebuffer.customer.grpc.service.impl;

import com.dailycodebuffer.customer.domain.responsitory.dsl.CustomerDslRepository;
import com.dailycodebuffer.customer.grpc.service.CustomerService;
import com.dailycodebuffer.customer.proto.CustomerPageResponse;
import com.dailycodebuffer.customer.proto.ListCustomerRequest;
import com.dailycodebuffer.customer.proto.ListCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDslRepository customerDslRepository;

    @Override
    public ListCustomerResponse listCustomers(ListCustomerRequest request) {
        var customers = customerDslRepository.getAllCustomer(request);
        return ListCustomerResponse.newBuilder()
                .setSuccess(true)
                .setData(ListCustomerResponse.Data.newBuilder()
                        .setPageResponse(CustomerPageResponse.newBuilder()
                                .setPage(request.getPageRequest().getPage())
                                .setSize(request.getPageRequest().getSize())
                                .setTotalElement(customers.getTotal())
                                .setTotalPage((customers.getTotal() + request.getPageRequest().getSize() - 1) / request.getPageRequest().getSize())
                                .build())
                        .addAllCustomers(customers.getItems().stream()
                                .map(s -> ListCustomerResponse.Customer.newBuilder()
                                        .setAge(s.getAge())
                                        .setClassId(s.getUserId())
                                        .setFirstName(s.getFirstName())
                                        .setLastName(s.getLastName())
                                        .setId(s.getId())
                                        .build())
                                .collect(Collectors.toUnmodifiableList()))
                        .build())
                .build();
    }
}
