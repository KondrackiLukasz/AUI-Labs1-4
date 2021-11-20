package com.example.demo.event.dto;

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
    private String name;

    public static Function<MilitaryUnit, CreateMilitaryUnitRequest> entityToDtoMapper() {
        return entity -> CreateMilitaryUnitRequest.builder()
                .name(entity.getName())
                .build();
    }
}
