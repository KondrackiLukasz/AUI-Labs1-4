package com.example.demo.repository;

import com.example.demo.entity.Soldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {

}
