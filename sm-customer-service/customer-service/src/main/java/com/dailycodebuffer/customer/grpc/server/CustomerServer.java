package com.dailycodebuffer.customer.grpc.server;

import com.dailycodebuffer.customer.grpc.service.CustomerService;
import com.dailycodebuffer.customer.proto.CustomerGrpcError;
import com.dailycodebuffer.customer.proto.CustomerServiceGrpc;
import com.dailycodebuffer.customer.proto.ListCustomerRequest;
import com.dailycodebuffer.customer.proto.ListCustomerResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CustomerServer extends CustomerServiceGrpc.CustomerServiceImplBase {
    private final CustomerService customerService;

    @Override
    public void listCustomers(ListCustomerRequest request, StreamObserver<ListCustomerResponse> responseObserver) {
        try {
            responseObserver.onNext(customerService.listCustomers(request));
            responseObserver.onCompleted();
        }
        catch (Exception e){
            responseObserver.onNext(ListCustomerResponse.newBuilder()
                    .setSuccess(false)
                    .setError(CustomerGrpcError.newBuilder()
                            .setCode("Not found")
                            .setException(e.getMessage())
                            .build())
                    .build());
            responseObserver.onCompleted();
            throw e;
        }
    }
}

