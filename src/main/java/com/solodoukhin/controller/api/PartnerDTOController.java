package com.solodoukhin.controller.api;

import com.solodoukhin.model.api.PartnerDTO;
import com.solodoukhin.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.10.18
 * Description: Api methods for Partner model
 */
@RestController
public class PartnerDTOController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerDTOController.class);

    private PartnerRepository repository;

    @Autowired
    public PartnerDTOController(PartnerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/partner")
    public List<PartnerDTO> getAllByCountry(@RequestParam("country") String country)
    {
        LOGGER.info("Call to PartnerDTOController.getAllByCountry with country = " + country);
        return this.repository.getAllByCountry(country).stream().map(PartnerDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/partner/getall/{country}")
    public List<PartnerDTO> getAll(@PathVariable("country") String country)
    {
        return this.getAllByCountry(country);
    }
}
