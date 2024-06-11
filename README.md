# Customer_Management_System

This project is a Customer Management System, providing functionalities for managing customer data effectively. It includes features for user authentication, CRUD operations on customer data, and syncing customer information from a remote API.

# Technologies_Used
# Backend: Java Spring Boot
# Frontend: HTML, CSS, JavaScript
# Database: MySQL
# Security: Spring Security with JWT authentication


# Getting Started
To run the application locally, follow these steps:
Clone this repository to your local machine.
Set up MySQL database and configure the database connection in the backend.
Navigate to the frontend directory and open the index.html file in your web browser to access the login page.
Log in with the provided credentials.
Once logged in, you can access other functionalities provided by the application.

# Features
User Authentication: Users can log in using their credentials.
Customer Management: Users can perform CRUD operations on customer information.
Sync Functionality: Users can sync customer data from a remote API and update the local database.
Search Functionality: Users can search for customers based on different criteria such as first name, last name, email, etc.
Setup
Ensure MySQL is installed on your system. Update the application.properties file in the backend directory with your MySQL database credentials:

# properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/sunbase?createTableIfNotExists=true
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
Usage
After setting up the database configuration, run the application. You can access the frontend by opening the index.html file in your web browser and logging in with the provided credentials.

# Contributing
Contributions are welcome! If you'd like to contribute to this project, please fork the repository and create a pull request.

# License
This project is licensed under the MIT License. See the LICENSE file for details.
