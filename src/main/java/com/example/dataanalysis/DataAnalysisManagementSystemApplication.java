package com.example.dataanalysis;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.service.DataService;
import com.example.dataanalysis.service.SampleDataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DataAnalysisManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(DataService dataService, SampleDataGenerator sampleDataGenerator) {
        return args -> {
            // Generate and save sample data
            List<DataEntity> sampleData = sampleDataGenerator.generateSampleData(100); // Generate 100 sample records
            dataService.saveAllData(sampleData);
            System.out.println("Sample data generated and saved to the database.");
        };
    }
}

