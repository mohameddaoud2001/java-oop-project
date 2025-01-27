package com.example.dataanalysis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "data_entries")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "budget", nullable = false)
    private Double budget;

    @Column(name = "spent")
    private Double spent;

    @Column(name = "status")
    private String status;

    // Constructors
    public DataEntity() {}

    // Constructor with all fields
    public DataEntity(String projectId, String projectName, Double budget, Double spent, String status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.budget = budget;
        this.spent = spent;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getSpent() {
        return spent;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

