package com.cinerikuy.movie.repository;

import com.cinerikuy.movie.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {
}
