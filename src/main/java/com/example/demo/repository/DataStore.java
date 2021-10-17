package com.example.demo.repository;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import com.example.demo.serialization.CloningUtility;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;


@Log
@Component
public class DataStore {

    private Set<MilitaryUnit> militaryUnits = new HashSet<>();

    private Set<Soldier> soldiers = new HashSet<>();

    public synchronized List<MilitaryUnit> findAllMilitaryUnits() {
        return new ArrayList<>(militaryUnits);
    }

    public synchronized Optional<MilitaryUnit> findMilitaryUnit(String name) {
        return militaryUnits.stream()
                .filter(profession -> profession.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createMilitaryUnit(MilitaryUnit militaryUnit) throws IllegalArgumentException {
        findMilitaryUnit(militaryUnit.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The militaryUnit name \"%s\" is not unique", militaryUnit.getName()));
                },
                () -> militaryUnits.add(CloningUtility.clone(militaryUnit)));
    }

    public synchronized List<Soldier> findAllSoldiers() {
        return soldiers.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Soldier> findSoldier(Long id) {
        return soldiers.stream()
                .filter(soldier -> soldier.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createSoldier(Soldier soldier) throws IllegalArgumentException {
        soldier.setId(findAllSoldiers().stream()
                .mapToLong(Soldier::getId)
                .max().orElse(0) + 1);
        soldiers.add(CloningUtility.clone(soldier));
    }

    public synchronized void updateSoldier(Soldier soldier) throws IllegalArgumentException {
        findSoldier(soldier.getId()).ifPresentOrElse(
                original -> {
                    soldiers.remove(original);
                    soldiers.add(CloningUtility.clone(soldier));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The soldier with id \"%d\" does not exist", soldier.getId()));
                });
    }

    public synchronized void deleteSoldier(Long id) throws IllegalArgumentException {
        findSoldier(id).ifPresentOrElse(
                original -> soldiers.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The soldier with id \"%d\" does not exist", id));
                });
    }

    public synchronized void deleteMilitaryUnit(String name) throws IllegalArgumentException {
        findMilitaryUnit(name).ifPresentOrElse(
                original -> soldiers.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            "The military unit - " + name + " does not exist");
                });
    }
}
