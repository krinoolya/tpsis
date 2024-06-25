package com.distributiongraduates.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Employees {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String faculty;
    private String tel;

    public Employees() {
        faculty = "Факультет";
        tel = "Контактный телефон";
    }

    public void set(String faculty, String tel) {
        this.faculty = faculty;
        this.tel = tel;
    }
}
