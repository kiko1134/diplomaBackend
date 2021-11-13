package com.example.diplomaDB.controller;

import com.example.diplomaDB.model.Service;
import com.example.diplomaDB.service.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/service")
public class ServiceController {
    private ServiceServiceImpl serviceService;

    @Autowired
    public ServiceController(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getServices(){return serviceService.getServices();}

    @PostMapping
    public void addService(@RequestBody Service service){serviceService.addNewService(service);}

    @DeleteMapping(path = "{service_id}")
    public void deleteService(@PathVariable("service_id") Long service_id){serviceService.deleteService(service_id);}

    @PutMapping(path = "{service_id}")
    public void updateService(@PathVariable(name = "service_id") Long service_id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Double price){
        serviceService.updateService(service_id,name,price);
    }
}
