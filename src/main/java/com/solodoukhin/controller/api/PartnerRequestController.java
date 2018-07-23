package com.solodoukhin.controller.api;

import com.solodoukhin.model.PartnerRequest;
import com.solodoukhin.repository.PartnerRequestRepository;
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
@RequestMapping("/partner-request")
public class PartnerRequestController {

    private static final Logger logger = LoggerFactory.getLogger(PartnerRequestController.class);
    private PartnerRequestRepository repository;

    @Autowired
    public PartnerRequestController(PartnerRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public PartnerRequest add(@RequestBody PartnerRequest request)
    {
        logger.info("Access PartnerRequestController.add with parameter = " + request);
        PartnerRequest createdRequest = null;
        try{
            createdRequest = this.repository.save(request);
        } catch (Exception e)
        {
            logger.error("Could not save partner request.", e);
        }
        return createdRequest;
    }

    @PutMapping("/update")
    public PartnerRequest update(@RequestBody PartnerRequest request)
    {
        logger.info("Access PartnerRequestController.update with parameter = " + request);
        PartnerRequest savedRequest;
        try{
            boolean exists = this.repository.existsById(request.getId());
            logger.info("PartnerRequest exists.");

            if(!exists)
            {
                logger.error("Could not update request because not found.");
                return null;
            }

            savedRequest = this.repository.save(request);
        } catch (Exception e)
        {
            logger.error("Could not update request.",e);
            return null;
        }
        return savedRequest;
    }

    @GetMapping("/all")
    public Iterable<PartnerRequest> getAll()
    {
        logger.info("Access PartnerRequestController.getAll");
        Iterable<PartnerRequest> requests = null;

        try{
            requests = this.repository.findAll();
        } catch (Exception e)
        {
            logger.error("Could not find partner requests.");
        }
        return requests;
    }

    @DeleteMapping("/{request_id}/delete")
    public ResponseEntity delete(@PathVariable("request_id") Integer requestId)
    {
        logger.info("Access PartnerRequestController.delete with parameter = " + requestId);
        try{
            Optional<PartnerRequest> request = this.repository.findById(requestId);
            if(request.isPresent())
            {
                repository.delete(request.get());
                logger.info("Request deleted.");
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e)
        {
            logger.error("Could not delete request.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
