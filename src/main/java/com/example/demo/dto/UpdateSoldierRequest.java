package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateSoldierRequest {
    private String name;

    private String rank;

    private int age;

    public static BiFunction<Soldier, UpdateSoldierRequest, Soldier> dtoToEntityUpdater() {
        return (soldier, request) -> {
            soldier.setName(request.getName());
            soldier.setRank(request.getRank());
            soldier.setAge(request.getAge());
            return soldier;
        };
    }
}
