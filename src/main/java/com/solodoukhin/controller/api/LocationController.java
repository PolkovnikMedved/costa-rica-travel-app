package com.solodoukhin.controller.api;

import com.solodoukhin.model.Location;
import com.solodoukhin.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Brief description here....
 *
 * @author viktor.solodoukhin@groups.be
 * @since 2018.07.19
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private LocationRepository repository;

    @Autowired
    public LocationController(LocationRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public Location add(@RequestBody Location location)
    {
        logger.info("Access LocationController.add with parameter = " + location);
        Location createdLocation = null;
        try{
            createdLocation = this.repository.save(location);
        } catch (Exception e)
        {
            logger.error("Could not save location.", e);
        }
        return createdLocation;
    }

    @PutMapping("/update")
    public Location update(@RequestBody Location location)
    {
        logger.info("Access LocationController.update with parameter = " + location);
        Location savedLocation;
        try{
            boolean exists = this.repository.existsById(location.getId());
            logger.info("Location exists.");

            if(!exists)
            {
                logger.error("Could not update location because not found.");
                return null;
            }

            savedLocation = this.repository.save(location);
        } catch (Exception e)
        {
            logger.error("Could not update location.",e);
            return null;
        }
        return savedLocation;
    }

    @GetMapping("/all")
    public Iterable<Location> getAll()
    {
        logger.info("Access LocationController.getAll");
        Iterable<Location> locations = null;

        try{
            locations = this.repository.findAll();
        } catch (Exception e)
        {
            logger.error("Could not find locations.");
        }
        return locations;
    }

    @DeleteMapping("/{location_id}/delete")
    public ResponseEntity delete(@PathVariable("location_id") Integer locationId)
    {
        logger.info("Access LocationController.delete with parameter = " + locationId);
        try{
            Optional<Location> location = this.repository.findById(locationId);
            if(location.isPresent())
            {
                repository.delete(location.get());
                logger.info("Location deleted.");
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e)
        {
            logger.error("Could not delete location.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
