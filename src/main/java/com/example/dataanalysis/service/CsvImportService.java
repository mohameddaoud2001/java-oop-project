package com.example.dataanalysis.service;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.repository.DataRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    @Autowired
    private DataRepository dataRepository;

    public void importCsvData(MultipartFile file) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            List<DataEntity> dataEntities = new ArrayList<>();
            // Assuming the first line is the header, skip it
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                // Map CSV columns to DataEntity fields
                DataEntity dataEntity = new DataEntity();
                dataEntity.setProjectId(nextLine[0]);
                dataEntity.setProjectName(nextLine[1]);
                dataEntity.setBudget(Double.parseDouble(nextLine[2]));
                dataEntity.setSpent(Double.parseDouble(nextLine[3]));
                dataEntity.setStatus(nextLine[4]);
                dataEntities.add(dataEntity);
            }
            dataRepository.saveAll(dataEntities);
        }
    }
}