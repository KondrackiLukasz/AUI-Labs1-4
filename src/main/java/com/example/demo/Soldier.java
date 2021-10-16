package com.example.demo;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Soldier {
    String name;
    String rank;
    int age;
    MilitaryUnit militaryUnit;
}
