package com.cinerikuy.cinema.repository;

import com.cinerikuy.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findByCinemaCode(String cinemaCode);
}
