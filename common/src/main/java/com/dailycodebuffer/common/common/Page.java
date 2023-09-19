package com.dailycodebuffer.common.common;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(setterPrefix = "set")
public class Page<T> {
    private List<T> items;
    private Long total;
}
