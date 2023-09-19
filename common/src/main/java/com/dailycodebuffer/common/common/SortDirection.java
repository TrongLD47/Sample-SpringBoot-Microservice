package com.dailycodebuffer.common.common;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
@AllArgsConstructor
public enum SortDirection {
    ASC("asc", "ascending"),
    DESC("desc", "descending");
    public final String shortName;
    public final String name;

    public static boolean isInvalid(String given){
        return !(ASC.shortName.equalsIgnoreCase(given) || ASC.name.equalsIgnoreCase(given) ||
                DESC.shortName.equalsIgnoreCase(given) || DESC.name.equalsIgnoreCase(given));
    }

    public static SortDirection parse(String given){
        if (!StringUtils.hasText(given)){
            return ASC;
        }
        return Arrays.stream(values())
                .filter(s -> s.name.equalsIgnoreCase(given) || s.shortName.equalsIgnoreCase(given))
                .findFirst()
                .orElse(ASC);
    }

    public static SortDirection parseStrictly(String given){
        if (isInvalid(given)){
            return null;
        }
        return Arrays.stream(values())
                .filter(s -> s.name.equalsIgnoreCase(given) || s.shortName.equalsIgnoreCase(given))
                .findFirst()
                .orElse(null);
    }
}
