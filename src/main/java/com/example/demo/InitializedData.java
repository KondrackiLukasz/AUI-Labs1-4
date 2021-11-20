package com.example.demo;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.service.MilitaryUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class InitializedData {

    private final MilitaryUnitService militaryUnitService;

    @Autowired
    public InitializedData(MilitaryUnitService militaryUnitService) {
        this.militaryUnitService = militaryUnitService;
    }

    @PostConstruct
    private synchronized void init() {

        MilitaryUnit u1320 = MilitaryUnit.builder().name("1320").maxCapacity(12).build();
        MilitaryUnit u2322 = MilitaryUnit.builder().name("2322").maxCapacity(34).build();
        MilitaryUnit u3321 = MilitaryUnit.builder().name("3321").maxCapacity(43).build();
        MilitaryUnit u4432 = MilitaryUnit.builder().name("4432").maxCapacity(66).build();

        militaryUnitService.create(u1320);
        militaryUnitService.create(u2322);
        militaryUnitService.create(u3321);
        militaryUnitService.create(u4432);
    }
}
