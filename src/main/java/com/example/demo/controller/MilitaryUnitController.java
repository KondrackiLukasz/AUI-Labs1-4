package com.example.demo.controller;

import com.example.demo.dto.CreateMilitaryUnitRequest;
import com.example.demo.dto.GetSoldiersResponse;
import com.example.demo.entity.MilitaryUnit;
import com.example.demo.service.MilitaryUnitService;
import com.example.demo.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/militaryUnits")
public class MilitaryUnitController {
    private final SoldierService soldierService;

    private final MilitaryUnitService militaryUnitService;

    @Autowired
    public MilitaryUnitController(SoldierService soldierService, MilitaryUnitService militaryUnitService) {
        this.soldierService = soldierService;
        this.militaryUnitService = militaryUnitService;
    }

    // TODO militaryUnit has one less field than in lab2
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

    @GetMapping("{name}/soldiers")
    public ResponseEntity<GetSoldiersResponse> getSoldiersFromUnit(@PathVariable("name") String name) {
        return ResponseEntity.ok(GetSoldiersResponse.entityToDtoMapper().apply(soldierService.findInMilitaryUnit(name)));

    }}
