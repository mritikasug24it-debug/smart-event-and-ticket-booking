package com.smartevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartevent.entity.Event;
import com.smartevent.repository.EventRepository;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = {
        "http://127.0.0.1:5174",
        "http://localhost:5174",
        "http://127.0.0.1:5173",
        "http://localhost:5173"
})
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // User + Admin can view events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Admin only - Add Event
    @PostMapping
    public ResponseEntity<?> addEvent(
            @RequestBody Event event,
            @RequestParam String role) {

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Access denied. Admin only.");
        }

        return ResponseEntity.ok(eventRepository.save(event));
    }

    // Admin only - Delete Event
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(
            @PathVariable Long id,
            @RequestParam String role) {

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Access denied. Admin only.");
        }

        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.ok("Event deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}