package com.solodoukhin.controller.api;

import com.solodoukhin.model.api.PartnerTypeDTO;
import com.solodoukhin.repository.PartnerTypeRepository;
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
 * Description: TODO
 */
@RestController
public class PartnerTypeDTOController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerTypeDTOController.class);

    private PartnerTypeRepository repository;

    @Autowired
    public PartnerTypeDTOController(PartnerTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("api/partnertype")
    public List<PartnerTypeDTO> getAllByCountry(@RequestParam("country") String country)
    {
        LOGGER.info("Call to PartnerTypeDTOController.getAllByCountry with country = " + country);
        return this.repository.getAllByCountry(country).stream().map(PartnerTypeDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/partnertype/getall/{country}")
    public List<PartnerTypeDTO> getAll(@PathVariable("country") String country)
    {
        return this.getAllByCountry(country);
    }
}
