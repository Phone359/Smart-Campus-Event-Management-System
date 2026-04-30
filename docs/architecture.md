# System Architecture

## Architectural Style
The Smart Campus Event Management System follows a layered N-tier architecture. This structure separates the system into different layers, each with a specific responsibility.

## Layers

### Presentation Layer
Handles user interaction through UI components such as login page, dashboards, and forms.

### Application Layer
Contains controllers that manage system operations such as authentication, event creation, and registration.

### Business Logic Layer
Applies system rules such as validation, event capacity checking, and role-based access control.

### Data Layer
Handles data storage and database communication.

## Design Decisions

- Singleton Pattern is used for database connection to ensure only one instance exists.
- Separation of concerns is applied to improve maintainability.
- Role-Based Access Control (RBAC) ensures security.

## Connection to Report 3
This architecture supports implementation, testing, and quality assurance by clearly separating responsibilities. It allows easier debugging, testing, and measurement of system quality.
