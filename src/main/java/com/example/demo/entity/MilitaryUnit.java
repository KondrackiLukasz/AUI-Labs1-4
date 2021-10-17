package com.example.demo.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MilitaryUnit implements Serializable {
    private String name;
    private int maxCapacity;
}
