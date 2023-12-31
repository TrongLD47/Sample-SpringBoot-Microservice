package com.dailycodebuffer.common.common;

import com.dailycodebuffer.common.util.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApiMessage {

    public static ApiMessage SUCCESS = new ApiMessage(0, "Success");
    public static ApiMessage FAILED = new ApiMessage(-1, "Failed");
    public static ApiMessage INVALID_DATA = new ApiMessage(-2, "Invalid data");
    public static ApiMessage CREATE_FAILED = new ApiMessage(-3, "Create failed");
    public static ApiMessage UPDATE_FAILED = new ApiMessage(-4, "Update failed");
    public static ApiMessage DELETE_FAILED = new ApiMessage(-5, "Delete failed");
    public static ApiMessage UNKNOWN_EXCEPTION = new ApiMessage(-6, "Unknown exception");

    public static ApiMessage UNAUTHORIZED = new ApiMessage(-10, "Unauthorized");
    public static ApiMessage LOGIN_FAILED = new ApiMessage(-11, "Login failed");

    private int code;
    private String message;
    private String localDateTime;
    private Object data;

    public ApiMessage(int code, String message) {
        this.code = code;
        this.message = message;
        this.localDateTime = DateTimeUtil.formatLocalDateTimeNow();
    }

    public static ApiMessage success(Object data) {
        ApiMessage apiMessage = ApiMessage.SUCCESS.clone();
        apiMessage.setData(data);
        return apiMessage;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ApiMessage clone() {
        ApiMessage instance = new ApiMessage(code, message);
        instance.setData(this.getData());
        return instance;
    }
}
