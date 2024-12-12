package src.main.java.com.example.dataanalysis.repository;

import com.example.dataanalysis.model.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
}
