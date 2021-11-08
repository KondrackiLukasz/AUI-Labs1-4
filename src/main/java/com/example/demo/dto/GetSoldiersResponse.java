package com.example.demo.dto;

import com.example.demo.entity.Soldier;
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
public class GetSoldiersResponse {

    @Singular
    private List<Soldier> soldiers;

    public static Function<Collection<com.example.demo.entity.Soldier>, GetSoldiersResponse> entityToDtoMapper() {
        return soldiers -> {
            GetSoldiersResponseBuilder response = GetSoldiersResponse.builder();
            soldiers.stream()
                    .map(soldier -> Soldier.builder()
                            .id(soldier.getId())
                            .name(soldier.getName())
                            .build())
                    .forEach(response::soldier);
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
    public static class Soldier {
        private Long id;
        private String name;
    }
}
