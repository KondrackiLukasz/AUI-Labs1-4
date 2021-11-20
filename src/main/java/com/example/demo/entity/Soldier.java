package com.example.demo.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "soldiers")
public class Soldier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String rank;

    private int age;

    @ManyToOne
    @JoinColumn(name = "military_unit")
    private MilitaryUnit militaryUnit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Soldier soldier = (Soldier) o;
        return id != null && Objects.equals(id, soldier.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
