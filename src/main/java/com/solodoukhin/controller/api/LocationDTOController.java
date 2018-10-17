package com.solodoukhin.controller.api;

import com.solodoukhin.model.api.LocationDTO;
import com.solodoukhin.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Solodoukhin Viktor
 * Date: 17.10.18
 * Description: TODO
 */
@RestController
public class LocationDTOController {

    private LocationRepository repository;

    @Autowired
    public LocationDTOController(LocationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/location")
    public List<LocationDTO> getAll()
    {
        return  this.repository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/location/getall/{country}")
    public List<LocationDTO> getAllByCountry(@PathVariable("country") String country)
    {
        return  this.repository.findAll().stream().map(LocationDTO::new).collect(Collectors.toList());
    }
}
