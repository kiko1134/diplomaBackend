package com.example.diplomaDB.controller;


import com.example.diplomaDB.model.Workshop;
import com.example.diplomaDB.service.WorkshopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/workshop")
public class WorkshopController {

    private WorkshopServiceImpl workshopService;

    @Autowired
    public WorkshopController(WorkshopServiceImpl workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping
    public List<Workshop> getWorkshops() {
        return workshopService.getWorkshops();
    }

    @PostMapping
    public void registerNewWorkshop(@RequestBody Workshop workshop) {
        workshopService.addNewWorkshop(workshop);
    }

    @DeleteMapping(path = "{workshop_id}")
    public void deleteWorkshop(@PathVariable("workshop_id") Long workshop_id) {
        workshopService.deleteWorkshop(workshop_id);
    }

    @PutMapping(path = "{workshop_id}")
    public void updateWorkshop(@PathVariable(name = "workshop_id") Long workshop_id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String description) {

        workshopService.updateWorkshop(workshop_id, name, email, phone, address, description);
    }


}
