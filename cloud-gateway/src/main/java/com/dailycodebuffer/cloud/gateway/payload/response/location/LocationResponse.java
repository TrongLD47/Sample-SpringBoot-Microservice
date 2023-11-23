package com.dailycodebuffer.cloud.gateway.payload.response.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationResponse {
    String address;
    Double longitude;
    Double latitude;
    String fromLocation;
    String toLocation;
}
