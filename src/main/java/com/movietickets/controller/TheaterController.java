package com.movietickets.controller;

import com.movietickets.model.Theater;
import com.movietickets.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TheaterController {
    private final TheaterService theaterService;

    @GetMapping
    public ResponseEntity<List<Theater>> getAllActiveTheaters() {
        return ResponseEntity.ok(theaterService.getAllActiveTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTheaterById(@PathVariable Long id) {
        Optional<Theater> theater = theaterService.getTheaterById(id);
        if (theater.isPresent()) {
            return ResponseEntity.ok(theater.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theater not found");
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Theater>> getTheatersByCity(@PathVariable String city) {
        return ResponseEntity.ok(theaterService.getTheatersByCity(city));
    }

    @PostMapping
    public ResponseEntity<Theater> addTheater(@RequestBody Theater theater) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theaterService.addTheater(theater));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheater(@PathVariable Long id, @RequestBody Theater theaterDetails) {
        try {
            Theater updatedTheater = theaterService.updateTheater(id, theaterDetails);
            return ResponseEntity.ok(updatedTheater);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheater(@PathVariable Long id) {
        try {
            theaterService.deleteTheater(id);
            return ResponseEntity.ok("Theater deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
