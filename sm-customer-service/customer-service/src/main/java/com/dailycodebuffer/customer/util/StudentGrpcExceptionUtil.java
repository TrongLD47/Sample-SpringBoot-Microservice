package com.dailycodebuffer.customer.util;

import com.dailycodebuffer.common.enums.ErrorCodeMap;
import com.dailycodebuffer.customer.proto.CustomerGrpcError;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentGrpcExceptionUtil {
    public static CustomerGrpcError asGrpcError(Exception e){
        var builder = CustomerGrpcError.newBuilder()
                .setCode(ErrorCodeMap.INTERNAL_ERROR.name())
                .setException(e.getClass().getSimpleName())
                .setMessage(getExceptionMessage(e))
                .setIsClientError(false);
//        if (e instanceof ObjectNotFoundException)
        return null;
    }

    private static String getExceptionMessage(Exception e) {
        return StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
    }
}
