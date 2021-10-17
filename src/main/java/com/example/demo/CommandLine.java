package com.example.demo;

import com.example.demo.entity.Soldier;
import com.example.demo.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLine implements CommandLineRunner {
    private SoldierService soldierService;

    @Autowired
    public CommandLine(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @Override
    public void run(String... args) throws Exception {
        soldierService.findAll().forEach(System.out::println);
    }

}
