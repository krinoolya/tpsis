package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Marital {
    SINGLE("Холост"),
    MARRIED("Женат/Замужем"),
    DIVORCED("Разведен"),
    ;
    private final String name;
}

