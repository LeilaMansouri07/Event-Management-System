
    package com.github.LeilaMansouri07.eventmanagmentsystem.repository;


import com.github.LeilaMansouri07.eventmanagmentsystem.model.Event;
import com.github.LeilaMansouri07.eventmanagmentsystem.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

        List<Event> findByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);

        Optional<Event> findByDateAndTimeSlot(LocalDate date, TimeSlot timeSlot);

        List<Event> findByDate(LocalDate date);


}
