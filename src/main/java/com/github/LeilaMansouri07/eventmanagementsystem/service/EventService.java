package com.github.LeilaMansouri07.eventmanagementsystem.service;


import com.github.LeilaMansouri07.eventmanagementsystem.exception.*;
import com.github.LeilaMansouri07.eventmanagementsystem.model.Event;
import com.github.LeilaMansouri07.eventmanagementsystem.model.TimeSlot;
import com.github.LeilaMansouri07.eventmanagementsystem.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public List<Event> getEventsForWeek(LocalDate weekStart, LocalDate weekEnd) {
        List<Event> events = repo.findByDateBetweenOrderByDateAsc(weekStart, weekEnd);
        if (events.isEmpty()) {
            throw new NoEventsFoundException("No events found between " + weekStart + " and " + weekEnd);
        }
        return events;
    }

    public void addEvent(Event event) {
        Optional<Event> conflict = repo.findByDateAndTimeSlot(event.getDate(), event.getTimeSlot());
        if (conflict.isPresent()) {
            throw new SlotAlreadyTakenException("This time is already taken, please try again");
        }
        repo.save(event);
        System.out.println("Event added successfully and saved: " + event.toString());
    }

    public void deleteEvent(Long id) {
        Optional<Event> eventOpt = repo.findById(id);
        if (eventOpt.isEmpty()) {
            throw new EventNotFoundException("Event with ID " + id + " not found.");
        }
        repo.deleteById(id);
    }

    public List<Event> getEventsByDate(LocalDate date) {
        List<Event> events = repo.findByDate(date);
        if (events.isEmpty()) {
            throw new NoEventsFoundException("No events found for date: " + date);
        }
        return events;
    }

    public List<TimeSlot> getFreeSlots(LocalDate date) {
        List<Event> events = repo.findByDate(date);
        boolean morningTaken = events.stream().anyMatch(e -> e.getTimeSlot() == TimeSlot.MORNING);
        boolean afternoonTaken = events.stream().anyMatch(e -> e.getTimeSlot() == TimeSlot.AFTERNOON);

        List<TimeSlot> free = new java.util.ArrayList<>();
        if (!morningTaken) free.add(TimeSlot.MORNING);
        if (!afternoonTaken) free.add(TimeSlot.AFTERNOON);

        if (free.isEmpty()) {
            throw new NoFreeSlotsException("No free slots available for date: " + date);
        }

        return free;
    }
}
