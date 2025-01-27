package com.example.dataanalysis.service;

import com.example.dataanalysis.model.DataEntity;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SampleDataGenerator {
    private final Faker faker = new Faker();

    public List<DataEntity> generateSampleData(int count) {
        List<DataEntity> dataEntities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String projectId = "PROJ-" + faker.number().digits(5);
            String projectName = faker.company().name() + " Project";
            double budget = ThreadLocalRandom.current().nextDouble(10000, 1000000);
            double spent = ThreadLocalRandom.current().nextDouble(0, budget);
            String status = getRandomStatus();

            DataEntity entity = new DataEntity(projectId, projectName, budget, spent, status);
            dataEntities.add(entity);
        }
        return dataEntities;
    }

    private String getRandomStatus() {
        String[] statuses = {"In Progress", "Completed", "On Hold", "Cancelled"};
        return statuses[ThreadLocalRandom.current().nextInt(statuses.length)];
    }
}