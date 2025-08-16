
package com.github.LeilaMansouri07.eventmanagmentsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

    @Entity
    public class Event {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;

        private LocalDate date;

        @Enumerated(EnumType.STRING)
        private TimeSlot timeSlot;

        public Event(String title, String description, LocalDate date, TimeSlot timeSlot) {
            this.title = title;
            this.description = description;
            this.date = date;
            this.timeSlot = timeSlot;
        }

        public Event() {

        }

        public Event(String busySlot, DayOfWeek dayOfWeek, TimeSlot timeSlot) {
            this.title = busySlot;
            this.date = LocalDate.now();
            this.timeSlot = timeSlot;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public TimeSlot getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
        }


        @Override
        public String toString() {
            return "Event{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", date=" + date +
                    ", timeSlot=" + timeSlot +
                    '}';
        }
    }


