package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DormitoryStatus {
    WAITING("В ожидании"),
    APPROVED("Одобрено"),
    REJECTED("Отклонено"),
    ;
    private final String name;
}

