package com.dailycodebuffer.cloud.gateway.payload.request.location;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LocationRequest implements Serializable {
    String address;
    Double longitude;
    Double latitude;
    String fromLocation;
    String toLocation;
}
