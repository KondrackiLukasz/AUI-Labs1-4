package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateSoldierRequest {

    private String name;

    private String rank;

    private int age;

    private String militaryUnit;

    public static Function<CreateSoldierRequest, Soldier> dtoToEntityMapper(Supplier<MilitaryUnit> militaryUnitSupplier) {
        return request -> Soldier.builder()
                .name(request.getName())
                .rank(request.getRank())
                .age(request.getAge())
                .militaryUnit(militaryUnitSupplier.get())
                .build();
    }
}
