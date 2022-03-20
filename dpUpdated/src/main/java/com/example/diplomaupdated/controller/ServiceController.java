package com.example.diplomaupdated.controller;


import com.example.diplomaupdated.DTO.GetServiceWorkshopDto;
import com.example.diplomaupdated.DTO.ServiceCreateDto;
import com.example.diplomaupdated.model.Service;
import com.example.diplomaupdated.repo.ServiceRepo;
import com.example.diplomaupdated.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "v1/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping
    public List<Service> getServices() {
        return serviceService.getServices();
    }

    @PostMapping
    public void addNewService(@RequestBody ServiceCreateDto service) {
        serviceService.addNewService(service.getName());
    }

    @DeleteMapping(path = "{service_id}")
    public void deleteService(@PathVariable("service_id") Long service_id) {
        serviceService.deleteService(service_id);
    }

    @PutMapping(path = "{service_id}")
    public void updateService(@PathVariable("service_id") Long service_id,
                              @RequestParam(required = false) String name) {
        serviceService.updateService(service_id, name);
    }

    @GetMapping(path = "{service_id}")
    public String getServiceNameById(@PathVariable String service_id) {
        return serviceService.getServiceNameById(Long.parseLong(service_id));
    }

}
