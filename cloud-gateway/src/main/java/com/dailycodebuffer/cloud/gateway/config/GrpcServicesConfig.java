package com.dailycodebuffer.cloud.gateway.config;

import com.dailycodebuffer.common.constant.ServiceConstant;
import com.dailycodebuffer.common.grpc.BaseGrpcServicesConfig;
import com.dailycodebuffer.department.service.DepartmentServiceGrpc;
import com.dailycodebuffer.location.service.LocationServiceGrpc;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GrpcServicesConfig extends BaseGrpcServicesConfig {

    public GrpcServicesConfig(EurekaClient eurekaClient) {
        super(eurekaClient);
    }

    @Bean
    public DepartmentServiceGrpc.DepartmentServiceBlockingStub departmentServiceBlockingStub() {
        InstanceInfo instanceInfo = getGrpcInstanceInfo(ServiceConstant.DEPARTMENT_SERVICE);
        return DepartmentServiceGrpc.newBlockingStub(newChannel(instanceInfo.getHostName(), instanceInfo.getPort()));
    }



    @Bean
    public LocationServiceGrpc.LocationServiceBlockingStub locationServiceBlockingStub() {
        InstanceInfo instanceInfo = getGrpcInstanceInfo(ServiceConstant.LOCATION_SERVICE);
        return LocationServiceGrpc.newBlockingStub(newChannel(instanceInfo.getHostName(), instanceInfo.getPort()));
    }
}
