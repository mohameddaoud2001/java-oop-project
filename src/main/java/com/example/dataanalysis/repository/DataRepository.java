package com.example.dataanalysis.repository;
import java.util.List;
import com.example.dataanalysis.model.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
    // Polymorphic query method resolved by Spring Data
    List<DataEntity> findByStatus(String status);
}