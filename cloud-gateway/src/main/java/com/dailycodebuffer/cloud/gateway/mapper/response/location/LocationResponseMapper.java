package com.dailycodebuffer.cloud.gateway.mapper.response.location;

import com.dailycodebuffer.ProtobufMapper;
import com.dailycodebuffer.ProtobufMapperConfig;
import com.dailycodebuffer.cloud.gateway.payload.response.location.LocationResponse;
import com.dailycodebuffer.location.protobuf.PLocation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author TrongLD
 * @since 11/22/2023
 */

@Mapper(config = ProtobufMapperConfig.class)
public interface LocationResponseMapper extends ProtobufMapper<LocationResponse, PLocation> {

    LocationResponseMapper INSTANCE = Mappers.getMapper(LocationResponseMapper.class);
}
