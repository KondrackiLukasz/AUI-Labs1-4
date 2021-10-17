package com.example.demo.service;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.repository.MilitaryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MilitaryUnitService {
    private MilitaryUnitRepository repository;

    @Autowired
    public MilitaryUnitService(MilitaryUnitRepository repository) {this.repository = repository;}

    public Optional<MilitaryUnit> find(String name) {
        return repository.find(name);
    }

    public void create(MilitaryUnit militaryUnit) {
        repository.create(militaryUnit);
    }
}
