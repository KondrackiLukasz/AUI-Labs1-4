package com.example.demo.controller;

import com.example.demo.dto.CreateSoldierRequest;
import com.example.demo.dto.GetSoldierResponse;
import com.example.demo.dto.GetSoldiersResponse;
import com.example.demo.dto.UpdateSoldierRequest;
import com.example.demo.entity.Soldier;
import com.example.demo.service.MilitaryUnitService;
import com.example.demo.service.SoldierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/soldiers")
public class SoldierController {
    private SoldierService soldierService;

    private MilitaryUnitService militaryUnitService;

    @Autowired
    public SoldierController(SoldierService soldierService, MilitaryUnitService militaryUnitService) {
        this.soldierService = soldierService;
        this.militaryUnitService = militaryUnitService;
    }

    @GetMapping
    public ResponseEntity<GetSoldiersResponse> getSoldiers() {
        return ResponseEntity.ok(GetSoldiersResponse.entityToDtoMapper().apply(soldierService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSoldierResponse> getSoldier(@PathVariable("id") long id) {
        return soldierService.find(id)
                .map(value -> ResponseEntity.ok(GetSoldierResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSoldier(@RequestBody CreateSoldierRequest request, UriComponentsBuilder builder) {
        try {
            Soldier soldier = CreateSoldierRequest
                    .dtoToEntityMapper(() -> militaryUnitService.find(request.getMilitaryUnit()).orElseThrow())
                    .apply(request);
            soldier = soldierService.create(soldier);
            return ResponseEntity.created(builder.pathSegment("api", "soldiers", "{id}")
                    .buildAndExpand(soldier.getId()).toUri()).build();
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSoldier(@PathVariable("id") long id) {
        Optional<Soldier> soldier = soldierService.find(id);
        if (soldier.isPresent()) {
            soldierService.delete(soldier.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSoldier(@RequestBody UpdateSoldierRequest request, @PathVariable("id") long id) {
        Optional<Soldier> soldier = soldierService.find(id);
        if (soldier.isPresent()) {
            UpdateSoldierRequest.dtoToEntityUpdater().apply(soldier.get(), request);
            soldierService.update(soldier.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
