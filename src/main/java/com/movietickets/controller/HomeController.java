package com.movietickets.controller;

import com.movietickets.service.MovieService;
import com.movietickets.service.TheaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MovieService movieService;
    private final TheaterService theaterService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", movieService.getAllActiveMovies());
        model.addAttribute("theaters", theaterService.getAllActiveTheaters());
        return "index";
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        model.addAttribute("movies", movieService.getAllActiveMovies());
        return "movies";
    }

    @GetMapping("/bookings")
    public String bookings() {
        return "bookings";
    }
}
