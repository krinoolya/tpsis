package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StudentStatus {
    BUDGET("Бюджет"),
    PAID("Платный"),
    ;
    private final String name;
}

