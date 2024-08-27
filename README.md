# Expense Tracker API

The Expense Tracker API is a backend service built with Spring Boot that allows users to manage their personal expenses.
This API provides functionality for users to create, read, update, and delete expenses, and also allows users to sign up
and log in to the application. Each user has their own set of expenses, securely managed with JWT (JSON Web Token) for
authentication and session handling.

## Features

- **User Authentication:**
    - Sign up as a new user.
    - Log in as an existing user.
    - JWT token generation and validation for secure user sessions.

- **Expense Management:**
    - List and filter past expenses:
        - Past week
        - Past month
        - Last 3 months
        - Custom (specify a start and end date)
    - Add new expenses.
    - Update existing expenses.
    - Remove existing expenses.

## Expense Categories

The following expense categories are supported:

- Groceries
- Leisure
- Electronics
- Utilities
- Clothing
- Health
- Others

## Technology Stack

- **Backend:** Spring Boot
- **Database:** MariaDB
- **ORM:** Hibernate (JPA)
- **Authentication:** JWT (JSON Web Token)

## Setup and Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Makechi02/expense-tracker-api.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd expense-tracker-api
   ```
   
3. **Configure Database:**
    - Ensure that MariaDB is installed and running on your local machine or server.
    - Create a database for the application:
      ```sql
      CREATE DATABASE expense_tracker_system;
      ```
    - Update the `application.yml` file with your MariaDB configuration:

   **application.yml**
   ```yml
   spring:
        datasource:
            url: jdbc:mariadb://localhost:3306/expense_tracker_system
            username: your-username
            password: your-password
            driver-class-name: org.mariadb.jdbc.Driver
        jpa:
            show-sql: true
            hibernate:
                ddl-auto: update
            properties:
                hibernate:
                    dialect: org.hibernate.dialect.MariaDBDialect
   ```

4. **Install Dependencies:**
   Run the following command to install all the dependencies:
   ```bash
   ./mvnw install
   ```

5. **Run the Application:**
   Start the Spring Boot application using the following command:
   ```bash
   ./mvnw spring-boot:run
   ```

[//]: # ()
[//]: # (6. **API Documentation:**)

[//]: # (   The API documentation can be accessed at `http://localhost:8080/swagger-ui.html` if you have integrated Swagger.)

## API Endpoints

Here are the main endpoints available in the Expense Tracker API:

- **Auth Routes:**
    - `POST /api/v1/users/auth/register` - Register a new user.
    - `POST /api/v1/users/auth/login` - Log in an existing user.


- **User Routes:**
    - `GET /api/v1/users` - Get all users.
    - `GET /api/v1/users/{userID}` - Get user by ID.
    - `GET /api/v1/users/email/{email}` - Get user by email.
    - `DELETE /api/v1/users/{userID}` - Delete user.


- **Expense Routes:**
    - `GET /api/v1/expenses` - List all expenses for the authenticated user.
    - `GET /api/v1/expenses?filter={time_period}` - List expenses filtered by the specified time period (e.g., past-week, past-month, past-3-months, custom).
    - `POST /api/v1/expenses` - Add a new expense.
    - `PUT /api/v1/expenses/{expenseID}` - Update an existing expense.
    - `DELETE /api/v1/expenses/{expenseID}` - Remove an existing expense.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any changes you'd like to make.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

This project is inspired by the backend roadmap on [roadmap.sh](https://roadmap.sh) and follows the guidelines provided in their [Expense Tracker API project](https://roadmap.sh/projects/expense-tracker-api).
