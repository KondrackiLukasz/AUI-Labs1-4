package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMilitaryUnitsResponse {
    @Singular
    private List<MilitaryUnit> militaryUnits;

    public static Function<Collection<com.example.demo.entity.MilitaryUnit>, GetMilitaryUnitsResponse> entityToDtoMapper() {
        return militaryUnits -> {
            GetMilitaryUnitsResponse.GetMilitaryUnitsResponseBuilder response = GetMilitaryUnitsResponse.builder();
            militaryUnits.stream()
                    .map(militaryUnit -> GetMilitaryUnitsResponse.MilitaryUnit.builder()
                            .name(militaryUnit.getName())
                            .build())
                    .forEach(response::militaryUnit);
            return response.build();
        };
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class MilitaryUnit {
        private String name;
    }
}
