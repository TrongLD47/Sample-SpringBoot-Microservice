package com.dailycodebuffer.cloud.gateway.mapper.request.location;

import com.dailycodebuffer.ProtobufMapper;
import com.dailycodebuffer.ProtobufMapperConfig;
import com.dailycodebuffer.cloud.gateway.payload.request.location.LocationRequest;
import com.dailycodebuffer.location.protobuf.PLocation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author TrongLD
 * @since 11/22/2023
 */

@Mapper(config = ProtobufMapperConfig.class)
public interface LocationRequestMapper extends ProtobufMapper<LocationRequest, PLocation> {

    LocationRequestMapper INSTANCE = Mappers.getMapper(LocationRequestMapper.class);
}
