package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSoldierResponse {
    private Long id;

    private String name;

    private String rank;

    private int age;

    private String militaryUnit;

    public static Function<Soldier, GetSoldierResponse> entityToDtoMapper() {
        return soldier -> GetSoldierResponse.builder()
                .id(soldier.getId())
                .age(soldier.getAge())
                .rank(soldier.getRank())
                .name(soldier.getName())
                .militaryUnit(soldier.getMilitaryUnit().getName())
                .build();
    }
}
