# Data Analysis Management System

## Project Overview

This project aims to develop a comprehensive data analysis management system using Java and Spring Boot. The system provides a user-friendly REST API to upload, store, analyze, and visualize data. It incorporates key object-oriented programming (OOP) concepts and leverages various libraries for data processing, analysis, and visualization.

## System Architecture

The system follows a layered architecture:

*   **Presentation Layer:** Exposes RESTful endpoints for client interaction.
*   **Business Logic Layer:** Handles core system functionality, including data ingestion, analysis, and visualization logic.
*   **Data Access Layer:** Interacts with the database to store and retrieve data using Spring Data JPA.

## Technology Stack

*   **Programming Language:** Java 17
*   **Framework:** Spring Boot 3.2.2
*   **Database:** PostgreSQL
*   **Build Tool:** Maven
*   **Data Analysis Libraries:**
    *   Apache Spark (for potential future integration)
*   **Visualization Library:** JFreeChart
*   **CSV Parsing Library:** OpenCSV
*   **Sample Data Generation:** Java Faker

## Functional Requirements

*   **Data Ingestion:**
    *   Import data from CSV files.
    *   Handle missing values and perform basic data cleaning (future enhancement).
*   **Data Exploration and Analysis:**
    *   Calculate descriptive statistics (mean, median, mode, standard deviation - partially implemented, more to be added).
    *   Generate data visualizations (basic bar chart implemented).
    *   Correlation analysis (future enhancement).
    *   Regression analysis (future enhancement).
    *   Time series analysis (future enhancement).
*   **Reporting and Dashboards:**
    *   Generate customizable reports (future enhancement).

## Non-Functional Requirements

*   **User-friendliness:** Simple and intuitive API for interaction.
*   **Scalability:** Designed to handle large datasets (with future optimizations).
*   **Security:** Data security and privacy to be considered (future enhancement).

## OOP Concepts Applied

*   **Encapsulation:** Data and methods are encapsulated within classes (e.g., `DataEntity`, `DataService`).
*   **Inheritance:** (Potentially to be used for different data entity types in the future).
*   **Polymorphism:** (Potentially to be used for different analysis or visualization methods in the future).
*   **Abstraction:** Interfaces and abstract classes can be used to define common behaviors (e.g., a `DataAnalysisService` interface).

## Project Setup

### Prerequisites

*   Java 17 or later
*   Maven
*   PostgreSQL (installed and running)
*   An IDE (e.g., VS Code, IntelliJ IDEA)

### Steps

1. **Clone the repository:**

    ```bash
    git clone <repository-url>
    cd data-analysis-system
    ```

2. **Database Configuration:**
    *   Create a PostgreSQL database named `government_monitoring`.
    *   Update the database connection details in `src/main/resources/application.properties`:

        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/government_monitoring
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        ```

3. **Build and Run:**
    *   Open the project in your IDE.
    *   Run the following Maven command in the terminal:

        ```bash
        mvn clean install
        mvn spring-boot:run
        ```

    *   Alternatively, you can run the `DataAnalysisManagementSystemApplication` class from your IDE.

## API Endpoints

| Endpoint                     | Method | Description                                          |
| ---------------------------- | ------ | ---------------------------------------------------- |
| `/api/data`                  | GET    | Retrieve all data entries.                           |
| `/api/data/{id}`             | GET    | Retrieve a data entry by ID.                        |
| `/api/data`                  | POST   | Add a new data entry (JSON body).                     |
| `/api/data/{id}`             | PUT    | Update an existing data entry (JSON body).           |
| `/api/data/{id}`             | DELETE | Delete a data entry by ID.                           |
| `/api/data/upload/csv`        | POST   | Upload data from a CSV file.                        |
| `/api/data/stats/mean-budget` | GET    | Calculate the mean budget of all data entries.        |
| `/api/data/chart/budget`     | GET    | Generate a bar chart of project budgets (PNG image). |

## Testing

You can use Postman to test the API endpoints. Import the provided Postman collection file (`data-analysis-api.json`) to get started.

## Future Enhancements

*   **Data Cleaning:** Implement more robust data cleaning and preprocessing capabilities.
*   **Advanced Analysis:** Add correlation, regression, and time series analysis features.
*   **Reporting:** Integrate a reporting library (e.g., JasperReports) for customizable reports.
*   **Security:** Implement authentication and authorization using Spring Security.
*   **Frontend:** Develop a user interface for easier interaction with the system.
*   **Scalability:** Optimize database queries and implement caching for improved performance.
*   **Dockerization:** Containerize the application for easier deployment.

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues to suggest improvements or report bugs.