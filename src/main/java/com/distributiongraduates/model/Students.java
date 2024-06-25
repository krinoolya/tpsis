package com.distributiongraduates.model;

import com.distributiongraduates.model.enums.Citizenship;
import com.distributiongraduates.model.enums.Marital;
import com.distributiongraduates.model.enums.StudentStatus;
import com.distributiongraduates.model.enums.YesNo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Students {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date;
    private String issued;
    private String issued_date;
    private String identity;
    private String address;
    private String tel_mob;
    private String tel_home;
    private String email;
    private String job;
    private String post;

    @Enumerated(EnumType.STRING)
    private Marital marital;
    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;
    @Enumerated(EnumType.STRING)
    private YesNo conscripted;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    public Students() {
        this.date = LocalDateTime.now().toString().substring(0, 10);
        this.issued = "Кем выдан";
        this.issued_date = LocalDateTime.now().toString().substring(0, 10);
        this.identity = "Идентификационный номер";
        this.address = "Адрес";
        this.tel_mob = "Мобильный телефон";
        this.tel_home = "Домашний номер";
        this.email = "Электронная почта";
        this.job = "Группа";
        this.post = "Студент";
        marital = Marital.SINGLE;
        citizenship = Citizenship.BELARUS;
        conscripted = YesNo.NO;
        status = null;
    }

    public void set(String date, String issued, String issued_date, String identity, String address, String tel_mob, String tel_home, String email, String job, String post, Marital marital, Citizenship citizenship, YesNo conscripted) {
        this.date = date;
        this.issued = issued;
        this.issued_date = issued_date;
        this.identity = identity;
        this.address = address;
        this.tel_mob = tel_mob;
        this.tel_home = tel_home;
        this.email = email;
        this.job = job;
        this.post = post;
        this.marital = marital;
        this.citizenship = citizenship;
        this.conscripted = conscripted;
    }
}
