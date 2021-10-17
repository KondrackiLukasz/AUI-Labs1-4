package com.example.demo;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import com.example.demo.service.MilitaryUnitService;
import com.example.demo.service.SoldierService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.time.LocalDate;


@Component
public class InitializedData {

    private final SoldierService soldierService;

    private final MilitaryUnitService militaryUnitService;

    @Autowired
    public InitializedData(SoldierService soldierService, MilitaryUnitService militaryUnitService) {
        this.soldierService = soldierService;
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

        Soldier james = Soldier.builder()
                .name("Ollie Haynes")
                .age(23)
                .rank("Private")
                .militaryUnit(u1320)
                .build();

        Soldier albert = Soldier.builder()
                .name("Devin Franklin")
                .age(22)
                .rank("Private First Class")
                .militaryUnit(u2322)
                .build();

        Soldier lauren = Soldier.builder()
                .name("Lauren Perry")
                .age(35)
                .rank("First Sergeant")
                .militaryUnit(u3321)
                .build();

        Soldier ricky = Soldier.builder()
                .name("Ricky Holt")
                .age(42)
                .rank("Sergeant Mayor")
                .militaryUnit(u4432)
                .build();

        Soldier bobby = Soldier.builder()
                .name("Bobby Dalton")
                .age(25)
                .rank("Private")
                .militaryUnit(u4432)
                .build();

        soldierService.create(bobby);
        soldierService.create(james);
        soldierService.create(albert);
        soldierService.create(lauren);
        soldierService.create(ricky);
    }

//    @SneakyThrows
//    private byte[] getResourceAsByteArray(String name) {
//        try (InputStream is = this.getClass().getResourceAsStream(name)) {
//            return is.readAllBytes();
//        }
//    }

}
