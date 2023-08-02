Scenario: Register a new project
  When I register a new project with the following details:
    | projectName   | projectStatus | projectStartDate | projectEndDate | budget  | description   | client  | projectManager | companyProject |
    | Test Project  | In Progress   | 2023-08-01       | 2023-12-31     | 500000  | Test project  | ClientA | ManagerA       | CompanyA       |
  Then the project should be registered successfully

Scenario: Delete a project
  Given a project with ID 123 exists
  When I delete the project with ID 123
  Then the project with ID 123 should be deleted successfully