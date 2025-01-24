package com.example.dataanalysis.controller;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

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
}