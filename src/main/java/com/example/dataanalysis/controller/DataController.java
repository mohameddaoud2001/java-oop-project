package src.main.java.com.example.dataanalysis.controller;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping
    public List<DataEntity> getAllData() {
        return dataService.getAllData();
    }

    @PostMapping
    public ResponseEntity<DataEntity> addData(@RequestBody DataEntity dataEntity) {
        DataEntity savedData = dataService.saveData(dataEntity);
        return ResponseEntity.ok(savedData);
    }
}
