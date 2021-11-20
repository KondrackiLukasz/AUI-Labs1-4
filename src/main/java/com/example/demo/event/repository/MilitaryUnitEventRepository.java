package com.example.demo.event.repository;

import com.example.demo.entity.MilitaryUnit;
import com.example.demo.event.dto.CreateMilitaryUnitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MilitaryUnitEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public MilitaryUnitEventRepository(@Value("${aui.soldiers.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(MilitaryUnit militaryUnit) {
        restTemplate.delete("/militaryUnits/{name}", militaryUnit.getName());
    }

    public void save(MilitaryUnit militaryUnit) {
        restTemplate.postForLocation("/militaryUnits", CreateMilitaryUnitRequest.entityToDtoMapper().apply(militaryUnit));
    }
}
