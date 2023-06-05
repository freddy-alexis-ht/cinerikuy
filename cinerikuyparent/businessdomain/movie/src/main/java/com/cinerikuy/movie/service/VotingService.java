package com.cinerikuy.movie.service;

import com.cinerikuy.movie.entity.Voting;
import com.cinerikuy.movie.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    public void post(long movieId, String username) {
        votingRepository.insertVoting(movieId, username);
    }
}
