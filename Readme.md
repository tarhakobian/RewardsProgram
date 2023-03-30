# Rewards Application

This is a **Java** application that uses **PostgreSQL** as the database and **Flyway** for version controlling the database schema. The application implements simple CRUD operations for customers and purchases. In addition, it includes a rewards calculation feature that calculates rewards points for each customer based on their purchase history.
Technologies Used

    Java 17
    PostgreSQL 42.5.4
    Flyway 9.5.1
    Spring Boot 3.0.5
    Spring JDBC
    Maven
    Docker

## **Database Setup**

The application uses PostgreSQL as the database. To set up the database, you can use the provided Docker Compose file:

    Install Docker and Docker Compose if you haven't already.
    Navigate to the root directory of the project in your terminal.
    Run the following command: docker-compose up -d

This will start a PostgreSQL container with the necessary configuration.

Alternatively, you can set up the database manually by following these steps:

    Create a new PostgreSQL database.
    Update the database connection details in the application.yml file.
    Run the Flyway migration to set up the database schema. The migration scripts are located in the src/main/resources/db/migration directory.

## **Rewards Calculation**

The rewards calculation feature is implemented in the RewardService class. The main functionality is implemented in the calculateRewards method. In addition, there is a main method in the class that can be used to quickly check the functionality of the rewards calculation.

The rewards calculation feature uses a PostgreSQL function calculate_rewards_points to count the number of points for each purchase. The SQL script for the function is located in the src/main/resources/db/migration/V2__RewardsFunction.sql file. The calculateRewards method uses JDBC templates to execute native SQL scripts to fetch the data from the database and calculate rewards points for each customer.

## **Conclusion**

This application demonstrates how to implement simple CRUD operations and a rewards calculation feature in Java using PostgreSQL as the database. It also shows how to use Flyway for version controlling the database schema and JDBC templates to execute native SQL scripts. In addition, the included Docker Compose file makes it easy to quickly start a PostgreSQL container for testing or development purposes.The presentation will also include a live demo of the application, which will make it easier to understand the functionality and how the different parts of the project fit together.