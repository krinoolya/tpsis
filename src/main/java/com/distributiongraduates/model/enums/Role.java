package com.distributiongraduates.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role implements GrantedAuthority {
    ADMIN("Декан"),
    EMPLOYEE("Сотрудник"),
    STUDENT("Выпускник"),
    USER("Студент"),
    ;

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

