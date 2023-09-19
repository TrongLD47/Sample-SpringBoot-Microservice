package com.dailycodebuffer.customer.grpc.service;

import com.dailycodebuffer.customer.proto.ListCustomerRequest;
import com.dailycodebuffer.customer.proto.ListCustomerResponse;

public interface CustomerService {
    ListCustomerResponse listCustomers(ListCustomerRequest request);
}
