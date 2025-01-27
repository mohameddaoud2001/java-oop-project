1. **Encapsulation (DataEntity.java)**
- Implementation: All class attributes are private and accessed via public getters/setters
- Benefits: 
  - Prevents direct field modification (`dataEntity.projectId = "newId"` would be invalid)
  - Enables validation in setters (though currently only in service layer)
  - Maintains data consistency through controlled access
- Example: 
  ```java
  public void setBudget(Double budget) {
    this.budget = budget;  // Could add validation here
  }
  ```

1. **Abstraction (DataService.java)**
- Implementation: Works through DataRepository interface
- Benefits:
  - Database operations are hidden behind simple method calls
  - Service remains unaware of SQL/JPA implementation details
  - Easy database switch via Spring profiles
- Key Interface: 
  ```java
  public interface DataRepository extends JpaRepository<DataEntity, Long> {}
  ```

1. **Polymorphism (DataRepository.java)**
- Implementation: Inherits from JpaRepository with parametric polymorphism
- Benefits:
  - Inherits CRUD operations without implementation
  - Enables custom query methods with Spring Data's method resolution
- Example of Polymorphic Usage:
  ```java
  dataRepository.save(dataEntity);  // Uses JPA implementation
  ```

1. **Inheritance (ProjectAnalysisData - mentioned extension)**
- Implementation Pattern:
  ```java
  public class ProjectAnalysisData extends DataEntity {
    // Inherits all DataEntity fields
    // Adds analysis-specific fields/methods
  }
  ```
- Benefits:
  - Reuses common fields (projectId, budget, etc.)
  - Enables polymorphic treatment of analysis data
  - Simplifies schema evolution through base class changes
