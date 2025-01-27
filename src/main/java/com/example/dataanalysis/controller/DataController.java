package com.example.dataanalysis.controller;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.service.CsvImportService;
import com.example.dataanalysis.service.DataService;
import com.opencsv.exceptions.CsvValidationException;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.dataanalysis.service.ChartService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @Autowired
    private CsvImportService csvImportService;

    @Autowired
    private ChartService chartService;

    @GetMapping
    public ResponseEntity<List<DataEntity>> getAllData() {
        List<DataEntity> data = dataService.getAllData();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataEntity> getDataById(@PathVariable Long id) {
        Optional<DataEntity> data = dataService.getDataById(id);
        return data.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DataEntity> addData(@RequestBody DataEntity dataEntity) {
        try {
            DataEntity savedData = dataService.saveData(dataEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
        } catch (IllegalArgumentException e) {
            // Handle validation exceptions from DataService
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataEntity> updateData(@PathVariable Long id,
                                                 @RequestBody DataEntity dataEntity) {
        try {
            if (!dataService.getDataById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            dataEntity.setId(id); // Ensure ID is set for update
            DataEntity updatedData = dataService.saveData(dataEntity);
            return ResponseEntity.ok(updatedData);
        } catch (IllegalArgumentException e) {
            // Handle validation exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        try {
            if (!dataService.getDataById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            dataService.deleteData(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/upload/csv")
    public ResponseEntity<?> uploadCsvData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a CSV file to upload.");
        }
        try {
            csvImportService.importCsvData(file);
            return ResponseEntity.ok("CSV data uploaded and saved successfully.");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing CSV file: " + e.getMessage());
        }
    }

    @GetMapping("/stats/mean-budget")
    public ResponseEntity<Double> getMeanBudget() {
        double meanBudget = dataService.calculateMeanBudget();
        return ResponseEntity.ok(meanBudget);
    }

    @GetMapping(value = "/chart/budget", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBudgetChart() {
        try {
            JFreeChart chart = chartService.createBudgetBarChart();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(outputStream, chart, 800, 600);
            return ResponseEntity.ok().body(outputStream.toByteArray());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}