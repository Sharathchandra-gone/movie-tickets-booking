package com.movietickets.service;

import com.movietickets.model.Theater;
import com.movietickets.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public List<Theater> getAllActiveTheaters() {
        return theaterRepository.findByIsActiveTrue();
    }

    public Optional<Theater> getTheaterById(Long theaterId) {
        return theaterRepository.findById(theaterId);
    }

    public List<Theater> getTheatersByCity(String city) {
        return theaterRepository.findByCityAndIsActiveTrue(city);
    }

    public Theater addTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Long theaterId, Theater theaterDetails) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
        
        if (theaterDetails.getName() != null) theater.setName(theaterDetails.getName());
        if (theaterDetails.getLocation() != null) theater.setLocation(theaterDetails.getLocation());
        if (theaterDetails.getCity() != null) theater.setCity(theaterDetails.getCity());
        if (theaterDetails.getPhone() != null) theater.setPhone(theaterDetails.getPhone());
        
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long theaterId) {
        theaterRepository.deleteById(theaterId);
    }
}
