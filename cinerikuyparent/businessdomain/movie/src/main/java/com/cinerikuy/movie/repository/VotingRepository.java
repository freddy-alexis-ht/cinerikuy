package com.cinerikuy.movie.repository;

import com.cinerikuy.movie.entity.Voting;
import com.cinerikuy.movie.entity.VotingPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, VotingPK> {
    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into voting (movie_id, username) values (:movie_id, :username)",
            nativeQuery = true)
    void insertVoting(@Param("movie_id") Long movieId, @Param("username") String username);

    Optional<Voting> findByVotingPKUsername(String username);
}
