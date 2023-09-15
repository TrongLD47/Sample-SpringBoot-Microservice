package com.dailycodebuffer.common.enums;

public enum Status {
    ACTIVE(1),
    INACTIVE(-1),
    PENDING(2);
    private final int value;
    private Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
