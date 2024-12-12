package src.main.java.com.example.dataanalysis.service;

import com.example.dataanalysis.model.DataEntity;
import com.example.dataanalysis.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    public List<DataEntity> getAllData() {
        return dataRepository.findAll();
    }

    public DataEntity saveData(DataEntity dataEntity) {
        return dataRepository.save(dataEntity);
    }
}
