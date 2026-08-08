package com.movietickets.repository;

import com.movietickets.model.Seat;
import com.movietickets.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTheater(Theater theater);
    List<Seat> findByTheaterAndStatus(Theater theater, Seat.SeatStatus status);
    Optional<Seat> findByTheaterAndRowAndNumber(Theater theater, String row, Integer number);
    
    @Query("SELECT s FROM Seat s WHERE s.theater = :theater AND s.status = 'AVAILABLE'")
    List<Seat> findAvailableSeats(Theater theater);
}
