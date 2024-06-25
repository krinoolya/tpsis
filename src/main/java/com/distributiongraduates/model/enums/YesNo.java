package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YesNo {
    NO("Нет"),
    YES("Да");
    private final String name;
}

