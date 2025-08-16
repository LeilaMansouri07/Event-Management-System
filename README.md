# Event-Managment-System
# 🎭 Amphi Scheduler 

This project is a simple program for managing **amphitheater reservations**. It is built with **Spring Boot** in a beginner-friendly way.

---

## ✨ Features

* Add an event to the weekly schedule
* View available amphitheater time slots (Saturday to Thursday, morning and afternoon)
* View all scheduled events with details
* Remove one or multiple events from the schedule
* Handle errors with **Global Exception Handling** (time already booked, event not found, no events available, no free slots, etc.)

---

## ⚡ Technologies Used

* Java 24
* Spring Boot
* Maven
* JUnit 5

---

## 📌 Notes

* All custom exceptions are defined in the `exception` package.
* Service layer contains business logic and throws exceptions.
* Controller layer handles these exceptions globally using `@ControllerAdvice` with `GlobalExceptionHandler` for proper HTTP responses.
* Repository layer generally returns empty results or relies on Spring Data exceptions.
