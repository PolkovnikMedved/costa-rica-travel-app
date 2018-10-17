package com.solodoukhin.controller.api;

import com.solodoukhin.model.api.DeviceDTO;
import com.solodoukhin.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Solodoukhin Viktor
 * Date: 17.10.18
 * Description: TODO
 */
@RestController
public class DeviceDTOController {

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
}
