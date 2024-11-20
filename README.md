# Hotel Booking API  

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green)](https://spring.io/projects/spring-boot)  
*A robust backend API for hotel booking functionality, built with Spring Boot, Spring Data JPA, and secured using Spring Security.*

---

## Table of Contents  

- [Introduction](#introduction)  
- [Features](#features)  
- [Technologies Used](#technologies-used)  
- [API Endpoints](#api-endpoints)  
- [Installation](#installation)  
- [Usage](#usage)  

---

## Introduction  

This project is the backend system for a hotel booking application. It provides RESTful APIs for managing users, hotels, rooms, bookings, and authentication, ensuring a seamless and secure experience for both customers and administrators.  

---

## Features  

- **User Management**: Register, login, and manage user profiles.  
- **Hotel and Room Management**: Add, update, delete, and fetch details about hotels and rooms.  
- **Booking System**: Make, view, and cancel bookings.  
- **Secure Authentication**: Role-based access control using Spring Security with JWT.  
- **Scalable Architecture**: Designed to handle growing data and traffic.  

---

## Technologies Used  

- **Spring Boot**: For creating the REST API.  
- **Spring Data JPA**: For interacting with the database.  
- **Spring Security**: For authentication and authorization.  
- **H2/ MySQL/PostgreSQL**: For data persistence (choose based on your setup, i have used mysql in this).
- **Maven**: For dependency management.  

---

## API Endpoints  

### Authentication  
- **POST** `/api/auth/register` - Register a new user.  
- **POST** `/api/auth/login` - User login to get a JWT token.  

### Users  
- **GET** `/api/users/{id}` - Fetch user details.  
- **PUT** `/api/users/{id}` - Update user profile.  

### Hotels  
- **GET** `/api/hotels` - Fetch list of hotels.  
- **POST** `/api/hotels` - Add a new hotel (Admin only).  
- **PUT** `/api/hotels/{id}` - Update hotel details (Admin only).  
- **DELETE** `/api/hotels/{id}` - Delete a hotel (Admin only).  

### Rooms  
- **GET** `/api/rooms` - Fetch list of rooms in a hotel.  
- **POST** `/api/rooms` - Add a new room (Admin only).  
- **PUT** `/api/rooms/{id}` - Update room details (Admin only).  
- **DELETE** `/api/rooms/{id}` - Delete a room (Admin only).  

### Bookings  
- **GET** `/api/bookings` - Fetch user bookings.  
- **POST** `/api/bookings` - Create a new booking.  
- **DELETE** `/api/bookings/{id}` - Cancel a booking.  

---

## Installation  

### Prerequisites  
- Java 11 or higher  
- Maven  
- MySQL/PostgreSQL  

### Steps  

1. Clone the repository:  
   ```bash  
   git clone https://github.com/yourusername/hotel-booking-api.git  
   ```

2. Navigate to the project directory:
   ```bash
   cd hotel-booking-api  
   ```

3. Configure the database in application.properties or application.yml.
   Build and run the application:
   ```bash
   mvn clean install  
   mvn spring-boot:run  
   ```

4. Access the API at http://localhost:8080.
 
 ---
 ## Usage
   - Use tools like Postman or Curl to test the APIs.
   - For authentication, include the JWT token in the Authorization header:
      ```bash
      Authorization: Bearer <JWT-Token>  
      ```
