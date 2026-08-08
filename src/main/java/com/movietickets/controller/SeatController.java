package com.movietickets.controller;

import com.movietickets.model.Seat;
import com.movietickets.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Seat>> getSeatsByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(seatService.getSeatsByTheater(theaterId));
    }

    @GetMapping("/available/{theaterId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long theaterId) {
        return ResponseEntity.ok(seatService.getAvailableSeats(theaterId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id) {
        Optional<Seat> seat = seatService.getSeatById(id);
        if (seat.isPresent()) {
            return ResponseEntity.ok(seat.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seat not found");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateSeatStatus(@PathVariable Long id, 
                                              @RequestParam Seat.SeatStatus status) {
        try {
            Seat updatedSeat = seatService.updateSeatStatus(id, status);
            return ResponseEntity.ok(updatedSeat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/initialize/{theaterId}")
    public ResponseEntity<?> initializeSeats(@PathVariable Long theaterId) {
        try {
            List<Seat> seats = seatService.initializeSeatsForTheater(theaterId);
            return ResponseEntity.status(HttpStatus.CREATED).body(seats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
