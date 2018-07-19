package com.solodoukhin.controller.old;

import com.solodoukhin.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Solodoukhin Viktor
 * Date: 15.07.18
 * Description: TODO
 */
@Controller
@RequestMapping("/location")
public class LocationController {

    private LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("locations", this.locationRepository.findAll());
        return "location/get-all";
    }

    @RequestMapping(path = "/create", method = {RequestMethod.GET})
    public String createOne()
    {
        return "location/create-one";
    }
}
