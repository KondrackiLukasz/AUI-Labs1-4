package com.example.demo.service;

import com.example.demo.entity.Soldier;
import com.example.demo.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldierService {
    private SoldierRepository repository;

    @Autowired
    public SoldierService(SoldierRepository repository) {
        this.repository = repository;
    }

    public Optional<Soldier> find(Long id) {
        return repository.find(id);
    }

    public List<Soldier> findAll() {
        return repository.findAll();
    }

    public void create(Soldier soldier) {
        repository.create(soldier);
    }

    public void delete(Long soldier) {
        repository.delete(repository.find(soldier).orElseThrow());
    }
}
