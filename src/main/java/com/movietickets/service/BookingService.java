package com.movietickets.service;

import com.movietickets.model.Booking;
import com.movietickets.model.Movie;
import com.movietickets.model.Seat;
import com.movietickets.model.Theater;
import com.movietickets.model.User;
import com.movietickets.repository.BookingRepository;
import com.movietickets.repository.MovieRepository;
import com.movietickets.repository.SeatRepository;
import com.movietickets.repository.TheaterRepository;
import com.movietickets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final SeatRepository seatRepository;

    public List<Booking> getUserBookings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUserOrderByBookingDateDesc(user);
    }

    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public Optional<Booking> getBookingByConfirmation(String confirmationNumber) {
        return bookingRepository.findByConfirmationNumber(confirmationNumber);
    }

    public Booking createBooking(Long userId, Long movieId, Long theaterId, 
                                  String selectedSeats, LocalDateTime showDateTime) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        // Calculate total price
        Double totalPrice = 0.0;
        String[] seatIds = selectedSeats.split(",");
        
        for (String seatId : seatIds) {
            Optional<Seat> seat = seatRepository.findById(Long.parseLong(seatId));
            if (seat.isPresent()) {
                totalPrice += seat.get().getPrice();
                seat.get().setStatus(Seat.SeatStatus.BOOKED);
                seatRepository.save(seat.get());
            }
        }

        Booking booking = new Booking(user, movie, theater, selectedSeats, totalPrice, showDateTime);
        booking.setConfirmationNumber(generateConfirmationNumber());
        booking.setStatus("CONFIRMED");
        
        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");
        
        // Release seats
        String[] seatIds = booking.getSelectedSeats().split(",");
        for (String seatId : seatIds) {
            Optional<Seat> seat = seatRepository.findById(Long.parseLong(seatId));
            if (seat.isPresent()) {
                seat.get().setStatus(Seat.SeatStatus.AVAILABLE);
                seatRepository.save(seat.get());
            }
        }
        
        bookingRepository.save(booking);
    }

    private String generateConfirmationNumber() {
        return "MTBS" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
