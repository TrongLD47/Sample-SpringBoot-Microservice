package com.dailycodebuffer.cloud.gateway.controller.location;


import com.dailycodebuffer.cloud.gateway.client.LocationGrpcClient;
import com.dailycodebuffer.common.common.ApiMessage;
import com.dailycodebuffer.location.protobuf.PLocationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationGrpcClient locationGrpcClient;

    @GetMapping
    public ApiMessage getLocationsByName(@RequestParam String search) {
        PLocationsResponse response = locationGrpcClient.getLocationsByName(search);
        return ApiMessage.success(response.getAddressesList());
    }
}
