package com.movietickets.repository;

import com.movietickets.model.Booking;
import com.movietickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByUserOrderByBookingDateDesc(User user);
    Optional<Booking> findByConfirmationNumber(String confirmationNumber);
    List<Booking> findByStatus(String status);
    List<Booking> findByShowDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
