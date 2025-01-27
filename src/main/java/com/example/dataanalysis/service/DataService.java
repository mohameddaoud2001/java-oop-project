package com.example.dataanalysis.service;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    public List<DataEntity> getAllData() {
        return dataRepository.findAll();
    }

    public Optional<DataEntity> getDataById(Long id) {
        return dataRepository.findById(id);
    }

    public DataEntity saveData(DataEntity dataEntity) {
        if (dataEntity.getProjectName() == null || dataEntity.getProjectName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project Name cannot be empty");
        }
        if (dataEntity.getBudget() == null || dataEntity.getBudget() < 0) {
            throw new IllegalArgumentException("Budget must be a non-negative value");
        }
        return dataRepository.save(dataEntity);
    }

    public List<DataEntity> saveAllData(List<DataEntity> dataEntities) {
        for (DataEntity dataEntity : dataEntities) {
            // Apply the same validation as in saveData
            if (dataEntity.getProjectName() == null || dataEntity.getProjectName().trim().isEmpty()) {
                throw new IllegalArgumentException("Project Name cannot be empty");
            }
            if (dataEntity.getBudget() == null || dataEntity.getBudget() < 0) {
                throw new IllegalArgumentException("Budget must be a non-negative value");
            }
        }
        return dataRepository.saveAll(dataEntities);
    }

    public void deleteData(Long id) {
        dataRepository.deleteById(id);
    }

    public double calculateMeanBudget() {
        List<DataEntity> allData = dataRepository.findAll();
        return allData.stream()
                .mapToDouble(DataEntity::getBudget)
                .average()
                .orElse(Double.NaN); // Handle case where there is no data
    }
}

