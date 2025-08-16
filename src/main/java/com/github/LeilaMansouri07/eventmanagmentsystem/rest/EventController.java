
package com.github.LeilaMansouri07.eventmanagmentsystem.rest;


import com.github.LeilaMansouri07.eventmanagmentsystem.model.Event;
import com.github.LeilaMansouri07.eventmanagmentsystem.model.TimeSlot;
import com.github.LeilaMansouri07.eventmanagmentsystem.service.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

        private final EventService service;

        public EventController(EventService service) {
            this.service = service;
        }


        @GetMapping("/week")
        public List<Event> getWeek(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start) {
            LocalDate end = start.plusDays(5);
            return service.getEventsForWeek(start, end);
        }


        @PostMapping
        public String addEvent(@RequestBody Event event) {
            service.addEvent(event);
            return "Event is added!";
        }


        @DeleteMapping("/{id}")
        public String deleteEvent(@PathVariable Long id) {
            service.deleteEvent(id);
            return "Event is deleted!";
        }


        @GetMapping("/free")
        public List<TimeSlot> freeSlots(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
            return service.getFreeSlots(date);
        }

        @GetMapping("/day")
        public List<Event> eventsByDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
            return service.getEventsByDate(date);
        }
}


