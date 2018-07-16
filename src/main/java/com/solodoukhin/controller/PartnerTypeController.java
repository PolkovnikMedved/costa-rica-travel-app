package com.solodoukhin.controller;

import com.solodoukhin.repository.PartnerTypeRepository;
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
@RequestMapping("/partner-type")
public class PartnerTypeController {

    private PartnerTypeRepository repository;

    @Autowired
    public PartnerTypeController(PartnerTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("types", repository.findAll());
        return "type/get-all";
    }

    @RequestMapping(path = "/create", method = {RequestMethod.GET})
    public String createOne()
    {
        return "type/create-one";
    }
}
