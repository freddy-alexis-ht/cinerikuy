package com.cinerikuy.cinema.service;

import com.cinerikuy.cinema.entity.Cinema;
import com.cinerikuy.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    @Autowired
    public CinemaRepository cinemaRepository;

    public List<Cinema> findAll() {
        List<Cinema> list = cinemaRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .filter(c -> c.isEnabled())
                .collect(Collectors.toList());
    }

    public Cinema findByCinemaCode(String cinemaCode) {
        Optional<Cinema> cinema = cinemaRepository.findByCinemaCode(cinemaCode);
        if(!cinema.isPresent()) return null;
        if(!cinema.get().isEnabled()) return null;
        return cinema.get();
    }

}
