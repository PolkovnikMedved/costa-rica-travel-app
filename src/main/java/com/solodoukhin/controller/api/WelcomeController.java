package com.solodoukhin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.07.18
 * Description: TODO
 */
@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public ResponseEntity welcome()
    {
        return ResponseEntity.ok().build();
    }
}
