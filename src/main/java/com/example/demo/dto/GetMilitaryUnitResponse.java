package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
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
public class GetMilitaryUnitResponse {
    private String name;
    private int maxCapacity;

    public static Function<MilitaryUnit, GetMilitaryUnitResponse> entityToDtoMapper() {
        return militaryUnit -> GetMilitaryUnitResponse.builder()
                .name(militaryUnit.getName())
                .maxCapacity(militaryUnit.getMaxCapacity())
                .build();
    }
}
