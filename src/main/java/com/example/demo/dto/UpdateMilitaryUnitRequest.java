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
public class UpdateMilitaryUnitRequest {
    private int maxCapacity;

    public static BiFunction<MilitaryUnit, UpdateMilitaryUnitRequest, MilitaryUnit> dtoToEntityUpdater() {
        return (militaryUnit, request) -> {
            militaryUnit.setMaxCapacity(request.getMaxCapacity());
            return militaryUnit;
        };
    }
}
