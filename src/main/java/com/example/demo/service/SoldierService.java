package com.example.demo.service;

import com.example.demo.entity.Soldier;
import com.example.demo.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoldierService {
    private final SoldierRepository repository;

    @Autowired
    public SoldierService(SoldierRepository repository) {
        this.repository = repository;
    }

    public Optional<Soldier> find(Long id) {
        return repository.findById(id);
    }

    public List<Soldier> findInMilitaryUnit(String militaryUnitName) {
        return repository.findAll().stream().filter(soldier ->
                soldier.getMilitaryUnit().getName().equals(militaryUnitName)).collect(Collectors.toUnmodifiableList());
    }

    public List<Soldier> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Soldier create(Soldier soldier) {
        return repository.save(soldier);
    }

    @Transactional
    public void update(Soldier soldier) {
        repository.save(soldier);
    }

    @Transactional
    public void delete(Long soldier) {
        repository.deleteById(soldier);
    }
}
