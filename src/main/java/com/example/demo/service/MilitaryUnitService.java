package com.example.demo.service;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.event.repository.MilitaryUnitEventRepository;
import com.example.demo.repository.MilitaryUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MilitaryUnitService {
    private MilitaryUnitRepository repository;
    private MilitaryUnitEventRepository eventRepository;

    @Autowired
    public MilitaryUnitService(MilitaryUnitRepository repository, MilitaryUnitEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<MilitaryUnit> find(String name) {
        return repository.findById(name);
    }

    public List<MilitaryUnit> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(MilitaryUnit militaryUnit) {
        repository.save(militaryUnit);
        eventRepository.save(militaryUnit);
    }

    @Transactional
    public void update(MilitaryUnit militaryUnit) {
        repository.save(militaryUnit);
    }

    @Transactional
    public void delete(MilitaryUnit militaryUnit) {
        eventRepository.delete(militaryUnit);
        repository.delete(militaryUnit);
    }
}
