package com.example.demo.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Soldier implements Serializable {
    private Long id;
    private String name;
    private String rank;
    private int age;
    private MilitaryUnit militaryUnit;
}
