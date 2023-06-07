package com.cinerikuy.cinema.service;

import com.cinerikuy.cinema.entity.Cinema;
import com.cinerikuy.cinema.entity.City;
import com.cinerikuy.cinema.repository.CinemaRepository;
import com.cinerikuy.cinema.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    public CinemaRepository cinemaRepository;
    @Autowired
    public CityRepository cityRepository;

    /** CINEMA */

    public List<Cinema> cinemaFindAll() {
        List<Cinema> list = cinemaRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .collect(Collectors.toList());
    }

    public Cinema cinemaFindById(long id) {
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if(!cinema.isPresent()) return null;
        return cinema.get();
    }

    public Cinema cinemaSaveUpdate(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void cinemaDelete(long id) {
        cinemaRepository.deleteById(id);
    }

    /** CITY */

    public List<City> cityFindAll() {
        List<City> list = cityRepository.findAll();
        if(list == null || list.isEmpty())
            return null;
        return list.stream()
                .collect(Collectors.toList());
    }

    public City cityFindById(long id) {
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()) return null;
        return city.get();
    }

    public City citySaveUpdate(City city) {
        return cityRepository.save(city);
    }

// Para conservar la integridad de la data, el admin no podrá eliminar registros de tablas-padre
//    public void cityDelete(long id) {
//        cityRepository.deleteById(id);
//    }

}
