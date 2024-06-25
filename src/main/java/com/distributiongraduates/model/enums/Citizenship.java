package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Citizenship {
    BELARUS("Беларусь"),
    RUSSIA("Россия"),
    POLAND("Польша"),
    USA("США"),
    EUROPE("Европа"),
    ;
    private final String name;
}

