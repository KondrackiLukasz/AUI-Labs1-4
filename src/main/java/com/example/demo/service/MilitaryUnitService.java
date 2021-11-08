package com.example.demo.service;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.repository.MilitaryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MilitaryUnitService {
    private MilitaryUnitRepository repository;

    @Autowired
    public MilitaryUnitService(MilitaryUnitRepository repository) {
        this.repository = repository;
    }

    public Optional<MilitaryUnit> find(String name) {
        return repository.findById(name);
    }

    public List<MilitaryUnit> findAll() {
        return repository.findAll();
    }

    @Transactional
    public MilitaryUnit create(MilitaryUnit militaryUnit) {
        return repository.save(militaryUnit);
    }

    @Transactional
    public void update(MilitaryUnit militaryUnit) {
        repository.save(militaryUnit);
    }

    @Transactional //TODO should it delete also soldiers?
    public void delete(String militaryUnit) {
        repository.deleteById(militaryUnit);
    }
}
