package com.example.demo.service;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.entity.Soldier;
import com.example.demo.repository.MilitaryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilitaryUnitService {
    private MilitaryUnitRepository repository;

    @Autowired
    public MilitaryUnitService(MilitaryUnitRepository repository) {this.repository = repository;}

    public Optional<MilitaryUnit> find(String name) {
        return repository.find(name);
    }

    public List<MilitaryUnit> findAll() {
        return repository.findAll();
    }

    public void create(MilitaryUnit militaryUnit) {
        repository.create(militaryUnit);
    }

    public void delete(String militaryUnit) {
        repository.delete(repository.find(militaryUnit).orElseThrow());
    }
}
