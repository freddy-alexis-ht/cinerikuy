package com.cinerikuy.movie.service;

import com.cinerikuy.movie.entity.Voting;
import com.cinerikuy.movie.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    public void post(long movieId, String username) {
        votingRepository.insertVoting(movieId, username);
    }

    public Voting findByUsername(String username) {
        Optional<Voting> voting = votingRepository.findByVotingPKUsername(username);
        if(!voting.isPresent()) return null;
        return voting.get();
    }

    public Long deleteByUsername(String username) {
        return votingRepository.deleteByVotingPKUsername(username);
    }

    public List<Voting> findAll() {
        List<Voting> votings = votingRepository.findAll();
        if(votings.isEmpty() || votings == null) return null;
        return votings;
    }

}
