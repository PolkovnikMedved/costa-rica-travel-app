package com.solodoukhin.controller.api;

import com.solodoukhin.model.Device;
import com.solodoukhin.model.api.DeviceDTO;
import com.solodoukhin.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Solodoukhin Viktor
 * Date: 17.10.18
 * Description: Controller for api methods
 */
@RestController
public class DeviceDTOController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDTOController.class);
    private DeviceRepository repository;

    @Autowired
    public DeviceDTOController(DeviceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/ios")
    public List<DeviceDTO> getAll()
    {
        return this.repository.findAll().stream().map(DeviceDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/ios/getdevices/{country}")
    public List<DeviceDTO> getAllByCountry(@PathVariable("country") String country)
    {
        return this.repository.findAll().stream().map(DeviceDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/ios/adddevice/{token}")
    public ResponseEntity<Device> addDevice(@PathVariable("token") String token)
    {
        LOGGER.info("Call to DeviceDTOController.addDevice with token = " + token);
        Device device;
        try{
            device = this.repository.save(new Device(token));
        } catch (Exception e)
        {
            LOGGER.error("Could not save device.", e);
            return ResponseEntity.notFound().build();
        }

        if(device == null){
            LOGGER.error("Could not save device. Returned device is null.");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(device);
    }
}
