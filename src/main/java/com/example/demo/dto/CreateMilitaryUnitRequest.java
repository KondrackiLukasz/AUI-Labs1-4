package com.example.demo.dto;

import com.example.demo.entity.MilitaryUnit;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateMilitaryUnitRequest {
    private int maxCapacity;
    private String name;

    public static Function<CreateMilitaryUnitRequest, MilitaryUnit> dtoToEntityMapper() {
        return request -> MilitaryUnit.builder()
                .name(request.getName())
                .build();
    }
}
