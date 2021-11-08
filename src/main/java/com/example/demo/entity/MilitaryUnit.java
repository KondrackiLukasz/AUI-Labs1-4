package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "military_units")
public class MilitaryUnit implements Serializable {

    @Id
    private String name;

    @JoinColumn(name = "max_capacity")
    private int maxCapacity;

    @OneToMany(mappedBy = "militaryUnit", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Soldier> soldiers;
}
