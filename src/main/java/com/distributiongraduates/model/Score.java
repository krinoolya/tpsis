package com.distributiongraduates.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Score {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int com;
    private int team;
    private int stress;
    private int ind;
    private int dis;
    private int summary;

    @OneToOne(fetch = FetchType.LAZY)
    private Users owner;

    public Score(Users owner) {
        com = 0;
        team = 0;
        stress = 0;
        ind = 0;
        dis = 0;
        summary = 0;
        this.owner = owner;
    }

    public Score(int com, int team, int stress, int ind, int dis) {
        this.com = com;
        this.team = team;
        this.stress = stress;
        this.ind = ind;
        this.dis = dis;
    }
}
