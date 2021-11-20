package com.example.demo;

import com.example.demo.service.MilitaryUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private MilitaryUnitService militaryUnitService;

    @Autowired
    public CommandLine(MilitaryUnitService militaryUnitService) {
        this.militaryUnitService = militaryUnitService;
    }

    @Override
    public void run(String... args) {
//        soldierService.findAll().forEach(System.out::println);
//        militaryUnitService.findAll().forEach(System.out::println);

        System.out.println("Type help for listing of available commands");
        String option = "";
        Scanner scan = new Scanner(System.in);
//        label:
//        while (!option.equals("q")) {
//            option = scan.next();
//            switch (option) {
//                case "help":
//                    System.out.println("Press:\n" +
//                            "   q - to quit\n" +
//                            "   printSoldiers - to print a list of all soldiers\n" +
//                            "   printUnits - to print a list of all military units\n" +
//                            "   deleteSoldier - to delete soldier\n" +
//                            "   addSoldier - to add a soldier");
//                    break;
//                case "printSoldiers":
//                    soldierService.findAll().forEach(System.out::println);
//                    break;
//                case "printUnits":
//                    militaryUnitService.findAll().forEach(System.out::println);
//                    break;
//                case "addSoldier":
//                    System.out.print("Enter soldier's first name: ");
//                    String firstName = scan.next();
//                    System.out.print("Enter soldier's last name: ");
//                    String fullName = firstName + " " + scan.next();
//                    System.out.print("Enter soldier's age: ");
//                    int age = scan.nextInt();
//                    System.out.print("Enter soldier's rank: ");
//                    String rank = scan.next();
//                    System.out.println("Available military units: ");
//                    militaryUnitService.findAll().forEach(System.out::println);
//                    System.out.print("Enter soldier's militaryUnit: ");
//                    String unitName = scan.next();
//                    militaryUnitService.find(unitName).ifPresentOrElse(
//                            original -> {
//                                Soldier newSoldier = Soldier.builder()
//                                        .name(fullName)
//                                        .age(age)
//                                        .rank(rank)
//                                        .militaryUnit(original)
//                                        .build();
//                                soldierService.create(newSoldier);
//                            },
//                            () -> System.out.println("No such unit, data has been discarded")
//                    );
//                    break;
//                case "deleteSoldier":
//                    System.out.print("Enter soldier's id: ");
//                    Long id = scan.nextLong();
//                    try {
//                        soldierService.delete(id);
//                    } catch (NoSuchElementException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "deleteUnit":
//                    System.out.print("Enter unit's name: ");
//                    String name = scan.next();
//                    try {
//                        militaryUnitService.delete(name);
//                    } catch (NoSuchElementException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case "q":
//                    break label;
//                default:
//                    System.out.println("Invalid input");
//                    break;
//            }
//        }
    }
}
