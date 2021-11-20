package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.MilitaryUnit;
import com.example.demo.service.MilitaryUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/militaryUnits")
public class MilitaryUnitController {

    private MilitaryUnitService militaryUnitService;

    @Autowired
    public MilitaryUnitController(MilitaryUnitService militaryUnitService) {
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
        militaryUnitService.create(militaryUnit);
        return ResponseEntity.created(builder.pathSegment("api", "militaryUnits", "{id}")
                .buildAndExpand(militaryUnit.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteMilitaryUnit(@PathVariable("name") String name) {
        Optional<MilitaryUnit> militaryUnit = militaryUnitService.find(name);
        if (militaryUnit.isPresent()) {
            militaryUnitService.delete(militaryUnit.get());
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
}
