package com.distributiongraduates.model;

import com.distributiongraduates.model.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users implements UserDetails {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;

    private int point;

    private String surname;
    private String name;
    private String patronymic;
    private String passport;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Students student;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Score score;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employees employee;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reviews> reviews = new ArrayList<>();
    @ManyToOne
    private Specialties specialization;

    public Users(String username, String password, Role role, String surname, String name, String patronymic, String passport) {
        this.role = role;
        this.username = username;
        this.password = passwordEncoder().encode(password);
        this.student = null;
        this.score = new Score(this);
        this.employee = null;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.passport = passport;
        point = 0;
        photo = "def/def.jpg";
    }

    public String getFio() {
        return surname + " " + name + " " + patronymic;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
