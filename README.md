# Trafin - Track Your Salary

A comprehensive personal finance management application built with Spring Boot and PostgreSQL.

## Overview

Trafin helps users manage their personal finances by tracking salary, expenses, bills, budgets, and providing timely notifications. The application offers a secure authentication system and a RESTful API for seamless integration with frontend applications.

## Features

- **User Management**
  - Secure registration and authentication using JWT
  - User profile management
  - Password encryption

- **Financial Tracking**
  - Salary management by month
  - Expense tracking and categorization
  - Bill management with due date reminders
  - Budget planning and monitoring
  - Transaction history

- **Financial Analysis**
  - Net worth calculation
  - Monthly expense vs. salary comparison
  - Expense categorization analysis
  - Spending patterns visualization

- **Bill Management**
  - Due date tracking
  - Payment status monitoring
  - Automated reminders

- **Data Visualization**
  - Spending patterns analysis
  - Budget vs. actual expense comparison
  - Financial health indicators

## Technology Stack

- **Backend**
  - Java 17
  - Spring Boot 3.x
  - Spring Data JPA
  - JWT Authentication
  - PostgreSQL Database

- **DevOps**
  - Docker & Docker Compose
  - Maven for build automation
  - Git for version control

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.9+
- Docker and Docker Compose (optional, for containerized deployment)
- PostgreSQL (if running without Docker)

### Running Locally

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/trafin.git
   cd trafin
   ```

2. Build the application:
   ```
   ./mvnw clean package
   ```

3. Run with Docker Compose (recommended):
   ```
   docker-compose up
   ```

   Or run directly with Maven:
   ```
   ./mvnw spring-boot:run
   ```

4. The application will be available at `http://localhost:8080`

### Environment Configuration

The application can be configured using environment variables:

- `SPRING_DATASOURCE_URL`: JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: Hibernate schema generation strategy
- `JWT_SECRET`: Secret key for JWT token generation
- `JWT_EXPIRATION`: Token expiration time in milliseconds

## API Documentation

The application provides a RESTful API with the following main endpoints:

- **Authentication**
  - `/signup` - Register a new user
  - `/login` - Authenticate and receive JWT token
  - `/logout` - Invalidate current session

- **User Management**
  - `/get-user-details` - Retrieve user profile information

- **Financial Management**
  - `/add-expense` - Record a new expense
  - `/get-transactions` - Retrieve all transactions
  - `/get-recent-transactions` - Get latest transactions
  - `/delete-transaction/{id}` - Remove a transaction

- **Salary Management**
  - `/add-salary` - Add or update monthly salary
  - `/get-all-salaries` - Retrieve salary history

- **Bill Management**
  - `/add-bill` - Create a new bill
  - `/get-all-bills` - Retrieve all bills

- **Budget Management**
  - `/update-budget` - Set or update budget parameters

- **Financial Analysis**
  - `/get-report` - Generate net worth report
  - `/get-all-month-expenses` - Monthly expense breakdown
  - `/get-expense-category` - Expense categorization

## Development

### Project Structure

```
trafin/
├── src/
│   ├── main/
│   │   ├── java/com/financetrack/development/
│   │   │   ├── controller/    # REST controllers
│   │   │   ├── model/         # Entity classes
│   │   │   ├── Repository/    # Data access layer
│   │   │   ├── service/       # Business logic
│   │   │   ├── config/        # Configuration classes
│   │   │   ├── dto/           # Data transfer objects
│   │   │   ├── security/      # Authentication components
│   │   │   └── middleware/    # Request interceptors
│   │   └── resources/         # Application properties, etc.
│   └── test/                  # Test classes
├── .mvn/wrapper/              # Maven wrapper
├── docker-compose.yml         # Docker Compose configuration
├── Dockerfile                 # Docker build configuration
└── pom.xml                    # Maven dependencies
```

### Building for Production

```
./mvnw clean package -P production
```

## License

This project is licensed under the [MIT License](LICENSE).

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
