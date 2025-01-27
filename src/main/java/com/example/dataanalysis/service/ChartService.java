package com.example.dataanalysis.service;

import com.example.dataanalysis.model.DataEntity;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {

    @Autowired
    private DataService dataService;

    public JFreeChart createBudgetBarChart() {
        List<DataEntity> data = dataService.getAllData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (DataEntity entity : data) {
            dataset.addValue(entity.getBudget(), "Budget", entity.getProjectName());
        }

        return ChartFactory.createBarChart(
                "Project Budgets",
                "Project Name",
                "Budget",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }
}