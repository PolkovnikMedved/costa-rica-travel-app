package com.solodoukhin.controller.webapp;

import com.solodoukhin.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.07.18
 * Description: Welcome controller
 */
@RestController
@RequestMapping("/")
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    private StorageService storageService;

    @Autowired
    public WelcomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity welcome()
    {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/images/{picture_name}")
    public ResponseEntity<Resource> servePicture(@PathVariable("picture_name") String pictureName) throws IOException
    {
        logger.info("Call to WelcomeController.servePicture() with picture name = " + pictureName);
        Path path = storageService.load(pictureName);
        if(path == null)
        {
            logger.info("Picture not found.");
            return ResponseEntity.notFound().build();
        }
        else
        {
            logger.info("Picture found !");
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity
                    .ok()
                    .contentLength(Files.size(path))
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }
    }
}
