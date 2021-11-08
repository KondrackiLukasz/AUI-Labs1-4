package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import com.example.demo.service.MilitaryUnitService;
import com.example.demo.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/militaryUnits")
public class MilitaryUnitController {
    private SoldierService soldierService;

    private MilitaryUnitService militaryUnitService;

    @Autowired
    public MilitaryUnitController(SoldierService soldierService, MilitaryUnitService militaryUnitService) {
        this.soldierService = soldierService;
        this.militaryUnitService = militaryUnitService;
    }

    @GetMapping
    public ResponseEntity<GetMilitaryUnitsResponse> getMilitaryUnits() {
        return ResponseEntity.ok(GetMilitaryUnitsResponse.entityToDtoMapper().apply(militaryUnitService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetMilitaryUnitResponse> getMilitaryUnit(@PathVariable("name") String name) {
        return militaryUnitService.find(name)
                .map(value -> ResponseEntity.ok(GetMilitaryUnitResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createMilitaryUnit(@RequestBody CreateMilitaryUnitRequest request, UriComponentsBuilder builder) {
        MilitaryUnit militaryUnit = CreateMilitaryUnitRequest
                .dtoToEntityMapper()
                .apply(request);
        militaryUnit = militaryUnitService.create(militaryUnit);
        return ResponseEntity.created(builder.pathSegment("api", "militaryUnits", "{id}")
                .buildAndExpand(militaryUnit.getName()).toUri()).build();

    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteMilitaryUnit(@PathVariable("name") String name) {
        Optional<MilitaryUnit> militaryUnit = militaryUnitService.find(name);
        if (militaryUnit.isPresent()) {
            militaryUnitService.delete(militaryUnit.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateMilitaryUnit(@RequestBody UpdateMilitaryUnitRequest request, @PathVariable("name") String name) {
        Optional<MilitaryUnit> militaryUnit = militaryUnitService.find(name);
        if (militaryUnit.isPresent()) {
            UpdateMilitaryUnitRequest.dtoToEntityUpdater().apply(militaryUnit.get(), request);
            militaryUnitService.update(militaryUnit.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{name}/soldiers")
    public ResponseEntity<GetSoldiersResponse> getSoldiersFromUnit(@PathVariable("name") String name) {
        return ResponseEntity.ok(GetSoldiersResponse.entityToDtoMapper().apply(soldierService.findAll().stream().filter(soldier -> soldier.getMilitaryUnit().getName().equals(name)).collect(Collectors.toUnmodifiableList())));

    }}