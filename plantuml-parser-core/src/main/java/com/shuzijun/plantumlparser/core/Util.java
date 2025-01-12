package com.shuzijun.plantumlparser.core;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static String listToString(List<?> list, String delimiter, String prefix, String suffix) {
        if (list.isEmpty()) {
            return "";
        }

        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(delimiter, prefix, suffix));
    }
}
