package com.solodoukhin.controller.api;

import com.solodoukhin.model.PartnerType;
import com.solodoukhin.repository.PartnerTypeRepository;
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
@RequestMapping("/partner-type")
public class PartnerTypeController {

    private static final Logger logger = LoggerFactory.getLogger(PartnerTypeController.class);
    private PartnerTypeRepository repository;

    @Autowired
    public PartnerTypeController(PartnerTypeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public PartnerType add(@RequestBody PartnerType type)
    {
        logger.info("Access PartnerTypeController.add with parameter = " + type);
        PartnerType createdRequest = null;
        try{
            createdRequest = this.repository.save(type);
        } catch (Exception e)
        {
            logger.error("Could not save partner type.", e);
        }
        return createdRequest;
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
