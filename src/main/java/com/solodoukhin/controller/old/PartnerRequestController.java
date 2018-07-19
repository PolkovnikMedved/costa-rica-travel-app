package com.solodoukhin.controller.old;

import com.solodoukhin.repository.PartnerRequestRepository;
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
@RequestMapping("/partner-request")
public class PartnerRequestController {

    private PartnerRequestRepository repository;

    @Autowired
    public PartnerRequestController(PartnerRequestRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAll(Model model)
    {
        model.addAttribute("requests", repository.findAll());
        return "request/get-all";
    }

    @RequestMapping(path = "/create", method = {RequestMethod.GET})
    public String createOne()
    {
        return "request/create-one";
    }
}
