package com.solodoukhin.controller;

import com.solodoukhin.model.Partner;
import com.solodoukhin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Solodoukhin Viktor
 * Date: 15.07.18
 * Description: TODO
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {

    private PartnerRepository repository;

    @Autowired
    public PartnerController(PartnerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("partners", this.repository.findAll());
        return "partner/get-all";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Integer id, Model model)
    {
        model.addAttribute("partner", this.repository.findById(id).orElse(new Partner()));
        return "partner/get-one";
    }

    @RequestMapping(path = "/create", method = {RequestMethod.GET})
    public String createOne()
    {
        return "partner/create-one";
    }
}
