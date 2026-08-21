# Smart Event and Ticket Booking System

## Project Description

The Smart Event and Ticket Booking System is a full-stack web application developed to simplify event management and online ticket booking. The system allows users to register, log in, view available events, and book tickets. Administrators can add and delete events through a separate admin interface.

## Technologies Used

* **Frontend:** React.js, HTML, CSS, JavaScript
* **Backend:** Spring Boot, Java
* **Database:** MySQL
* **API:** REST APIs
* **Development Tools:** Eclipse, Visual Studio Code
* **Build Tool:** Maven
* **Email Service:** Gmail SMTP

## Main Features

* User Registration and Login
* Admin Login and Event Management
* Add New Events
* View Available Events
* Delete Events
* Online Ticket Booking
* Automatic Ticket Availability Update
* Booking Confirmation through Email
* MySQL Database Integration
* REST API based Frontend–Backend Communication
* Error Handling and Validation

## Modules

### 1. User Module

Users can register, log in, and access the event booking features.

### 2. Admin Module

The administrator can add new events and delete existing events.

### 3. Event Module

Users can view available events with details such as event name, location, date, time, price, and available tickets.

### 4. Booking Module

Users can select an event, enter the required number of tickets, and complete the booking.

### 5. Email Module

The system sends booking-related confirmation emails to the registered user's email address.

### 6. Database Module

MySQL is used to store users, events, and booking information.

## System Architecture

The application follows a three-layer architecture:

**React.js Frontend → Spring Boot REST API → MySQL Database**

The frontend communicates with the backend through REST APIs, while Spring Boot handles business logic and database operations.

## Project Structure

```text
smart-event-and-ticket-booking/
│
├── smart-event/
│   └── Spring Boot Backend
│
└── smart-event-frontend/
    └── React.js Frontend
```

## How to Run

### Backend

1. Open the `smart-event` project in Eclipse.
2. Configure the MySQL database.
3. Update the database configuration in `application.properties`.
4. Run the Spring Boot application.
5. The backend runs on port `8080`.

### Frontend

1. Open the `smart-event-frontend` folder in Visual Studio Code.
2. Open the terminal.
3. Run:

```bash
npm install
npm run dev
```

4. Open the localhost URL shown by Vite.

## API Integration

The React.js frontend communicates with the Spring Boot backend using REST APIs for:

* User registration
* User login
* Event management
* Ticket booking
* Booking details

## Database

The system uses MySQL to manage:

* Users
* Events
* Bookings

## Conclusion

The Smart Event and Ticket Booking System provides a simple and efficient platform for managing events and booking tickets online. The integration of React.js, Spring Boot, REST APIs, and MySQL provides a reliable full-stack solution with separate user and admin functionalities.
