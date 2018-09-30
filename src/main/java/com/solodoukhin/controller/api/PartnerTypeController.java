package com.solodoukhin.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solodoukhin.model.PartnerType;
import com.solodoukhin.repository.PartnerTypeRepository;
import com.solodoukhin.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Brief description here....
 *
 * @author viktor.solodoukhin@groups.be
 * @since 2018.07.19
 */
@RestController
@RequestMapping("/partner-type")
public class PartnerTypeController {

    private static final Logger logger = LoggerFactory.getLogger(PartnerTypeController.class);
    private PartnerTypeRepository repository;
    private StorageService storageService;

    @Autowired
    public PartnerTypeController(PartnerTypeRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    public PartnerType getOne(@PathVariable("id") Integer id)
    {
        logger.info("Access PartnerType.getOne with parameter = " + id);
        Optional<PartnerType> type = this.repository.findById(id);
        return type.orElseGet(PartnerType::new);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PartnerType> create(@RequestParam("partner-type") String partnerTypeJSON, @RequestParam("file") MultipartFile file)
    {
        logger.info("Call to PartnerTypeController.create with file = " + file.getOriginalFilename(), ", partner type = " + partnerTypeJSON);
        PartnerType partnerType;
        try{
            partnerType = new ObjectMapper().readValue(partnerTypeJSON, PartnerType.class);
            String createdFileName = this.storageService.store(file);
            if(createdFileName != null)
            {
                partnerType.setPicture(createdFileName);
                this.repository.save(partnerType);
                logger.info("Partner type has been saved. Created picture name = " + createdFileName);
            }
            else
            {
                return ResponseEntity.badRequest().body(null);
            }
        }
        catch (Exception e)
        {
            logger.error("Could not create partner type.", e);
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(partnerType);
    }

    @PutMapping("/update")
    public PartnerType update(@RequestBody PartnerType type)
    {
        logger.info("Access PartnerTypeController.update with parameter = " + type);
        PartnerType savedRequest;
        try{
            boolean exists = this.repository.existsById(type.getId());
            logger.info("Partner type exists.");

            if(!exists)
            {
                logger.error("Could not update type because not found.");
                return null;
            }

            savedRequest = this.repository.save(type);
        } catch (Exception e)
        {
            logger.error("Could not update type.",e);
            return null;
        }
        return savedRequest;
    }

    @GetMapping("/all")
    public Iterable<PartnerType> getAll()
    {
        logger.info("Access PartnerTypeController.getAll");
        Iterable<PartnerType> types = null;

        try{
            types = this.repository.findAll();
        } catch (Exception e)
        {
            logger.error("Could not find partner types.");
        }
        return types;
    }

    @DeleteMapping("/{type_id}/delete")
    public ResponseEntity delete(@PathVariable("type_id") Integer typeId)
    {
        logger.info("Access PartnerTypeController.delete with parameter = " + typeId);
        try{
            Optional<PartnerType> type = this.repository.findById(typeId);
            if(type.isPresent())
            {
                repository.delete(type.get());
                logger.info("Partner type deleted.");
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e)
        {
            logger.error("Could not delete type.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
