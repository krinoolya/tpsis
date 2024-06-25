package com.distributiongraduates.model;

import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.StudentStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Specialties {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int count;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> users;

    public Specialties(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void set(String name, int budget) {
        this.name = name;
        this.count = budget;
    }

    public int getSum() {
        return count;
    }

    public int getEnrolled() {
        return users.stream().reduce(0, (i, user) -> {
            if (user.getRole() == Role.STUDENT) {
                return i + 1;
            } else {
                return i;
            }
        }, Integer::sum);
    }

    public int getEnrolledBudget() {
        return users.stream().reduce(0, (i, user) -> {
            if (user.getRole() == Role.STUDENT && user.getStudent().getStatus() == StudentStatus.BUDGET) {
                return i + 1;
            } else {
                return i;
            }
        }, Integer::sum);
    }

    public int getEnrolledPaid() {
        return users.stream().reduce(0, (i, user) -> {
            if (user.getRole() == Role.STUDENT && user.getStudent().getStatus() == StudentStatus.PAID) {
                return i + 1;
            } else {
                return i;
            }
        }, Integer::sum);
    }

}
