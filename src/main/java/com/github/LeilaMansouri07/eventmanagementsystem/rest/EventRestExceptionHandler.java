package com.github.LeilaMansouri07.eventmanagementsystem.rest;

import com.github.LeilaMansouri07.eventmanagementsystem.exception.EventNotFoundException;
import com.github.LeilaMansouri07.eventmanagementsystem.exception.NoEventsFoundException;
import com.github.LeilaMansouri07.eventmanagementsystem.exception.NoFreeSlotsException;
import com.github.LeilaMansouri07.eventmanagementsystem.exception.SlotAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EventRestExceptionHandler {

    private ResponseEntity<Object> buildResponse(Exception ex, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", status.value());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(SlotAlreadyTakenException.class)
    public ResponseEntity<Object> handleSlotTaken(SlotAlreadyTakenException ex) {
        return buildResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> handleEventNotFound(EventNotFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoEventsFoundException.class)
    public ResponseEntity<Object> handleNoEventsFound(NoEventsFoundException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoFreeSlotsException.class)
    public ResponseEntity<Object> handleNoFreeSlots(NoFreeSlotsException ex) {
        return buildResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
