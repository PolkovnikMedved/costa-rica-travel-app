package com.solodoukhin.controller.webapp;

import com.solodoukhin.model.Partner;
import com.solodoukhin.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/partner")
public class PartnerController {

    private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);
    private PartnerRepository repository;

    @Autowired
    public PartnerController(PartnerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add")
    public Partner add(@RequestBody Partner partner)
    {
        logger.info("Access PartnerController.add with parameter = " + partner);
        Partner createdPartner = null;
        try{
            createdPartner = this.repository.save(partner);
        } catch (Exception e)
        {
            logger.error("Could not save partner.",e );
        }
        return createdPartner;
    }

    @PutMapping("/update")
    public Partner update(@RequestBody Partner partner)
    {
        logger.info("Access PartnerController.update with parameter = " + partner);
        Partner updatedPartner = null;
        try{
            boolean exists = this.repository.existsById(partner.getId());

            if(!exists){
                logger.error("Could not update partner because not found.");
                return null;
            }

            updatedPartner = this.repository.save(partner);
        } catch (Exception e)
        {
            logger.error("Could not update partner.", e);
        }
        return updatedPartner;
    }

    @GetMapping("/{partner_id}")
    public Partner getOne(@PathVariable("partner_id") Integer partnerId)
    {
        logger.info("Access PartnerController.getOne with parameter = " + partnerId);
        Partner partner = null;
        try{
            partner = this.repository.getOne(partnerId);
        } catch (Exception e)
        {
            logger.error("Could not find partner with id = " + partnerId);
        }

        return partner;
    }

    @GetMapping("/all")
    public Iterable<Partner> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page)
    {
        logger.info("Access PartnerController.getAll with page = " + page);
        if(page == null) page = 0;

        if(page >= 1){
            // Front-end problem forces us to mad solutions
            page--;
        }
        Page<Partner> partners = null;
        try{
            partners = this.repository.findAll(PageRequest.of(page, 15, Sort.Direction.ASC, "id"));
        } catch (Exception e)
        {
            logger.error("Could not retrieve partners.", e);
        }
        return partners;
    }

    @DeleteMapping("/{partner_id}/delete")
    public ResponseEntity delete(@PathVariable("partner_id") Integer partnerId)
    {
        logger.info("Access PartnerController.delete with parameter = " + partnerId);
        try{
            Optional<Partner> partner = this.repository.findById(partnerId);
            if(partner.isPresent())
            {
                repository.delete(partner.get());
                logger.info("Partner deleted.");
                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e)
        {
            logger.error("Could not delete partner.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
