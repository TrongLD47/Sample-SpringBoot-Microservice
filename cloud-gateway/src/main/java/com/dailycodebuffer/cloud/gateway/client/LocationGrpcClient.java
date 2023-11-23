package com.dailycodebuffer.cloud.gateway.client;

import com.dailycodebuffer.location.protobuf.PLocationsResponse;
import com.dailycodebuffer.location.service.LocationServiceGrpc;
import com.google.protobuf.StringValue;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationGrpcClient {

    @GrpcClient("grpc-location-service")
    private final LocationServiceGrpc.LocationServiceBlockingStub locationServiceBlockingStub;

    public PLocationsResponse getLocationsByName(String search) {
        return locationServiceBlockingStub.getLocationByName(StringValue.of(search));
    }
}
