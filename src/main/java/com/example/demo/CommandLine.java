package com.example.demo;

import com.example.demo.entity.Soldier;
import com.example.demo.service.MilitaryUnitService;
import com.example.demo.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//TODO add functionalities
@Component
public class CommandLine implements CommandLineRunner {
    private SoldierService soldierService;
    private MilitaryUnitService militaryUnitService;

    @Autowired
    public CommandLine(SoldierService soldierService, MilitaryUnitService militaryUnitService) {
        this.soldierService = soldierService;
        this.militaryUnitService = militaryUnitService;
    }

    @Override
    public void run(String... args) throws Exception {
        soldierService.findAll().forEach(System.out::println);
//        militaryUnitService.findAll().forEach(System.out::println);
    }
}
