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
    private DataRepository dataRepository; // Abstraction through interface

    public List<DataEntity> getAllData() {
        return dataRepository.findAll();
    }

    public Optional<DataEntity> getDataById(Long id) {
        return dataRepository.findById(id);
    }

    public DataEntity saveData(DataEntity dataEntity) {
        // Basic validation before saving (can be expanded)
        if (dataEntity.getProjectName() == null || dataEntity.getProjectName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project Name cannot be empty");
        }
        if (dataEntity.getBudget() == null || dataEntity.getBudget() < 0) {
            throw new IllegalArgumentException("Budget must be a non-negative value");
        }
        return dataRepository.save(dataEntity);
    }

    public void deleteData(Long id) {
        dataRepository.deleteById(id);
    }

    // Example of a custom query method that you might add later:
    // public List<DataEntity> findByStatus(String status) {
    //     return dataRepository.findByStatus(status);
    // }
}



