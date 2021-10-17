package com.example.demo.repository;

import com.example.demo.entity.Soldier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SoldierRepository implements Repository<Soldier, Long> {
    private DataStore store;

    @Autowired
    public SoldierRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Soldier> find(Long id) {
        return store.findSoldier(id);
    }

    @Override
    public List<Soldier> findAll() {
        return store.findAllSoldiers();
    }

    @Override
    public void create(Soldier entity) {
        store.createSoldier(entity);
    }

    @Override
    public void delete(Soldier entity) {
        store.deleteSoldier(entity.getId());
    }

    @Override
    public void update(Soldier entity) {
        store.updateSoldier(entity);
    }

}
