package src.main.java.com.example.dataanalysis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "data_entries")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_name", nullable = false)
    private String dataName;

    @Column(name = "value", nullable = false)
    private double value;

    // Constructors
    public DataEntity() {}

    public DataEntity(String dataName, double value) {
        this.dataName = dataName;
        this.value = value;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
