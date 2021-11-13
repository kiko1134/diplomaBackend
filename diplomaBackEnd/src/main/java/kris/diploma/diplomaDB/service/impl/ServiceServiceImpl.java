package kris.diploma.diplomaDB.service.impl;

import kris.diploma.diplomaDB.model.Service;
import kris.diploma.diplomaDB.repository.ServiceRepository;
import kris.diploma.diplomaDB.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    @Override
    public void addNewService(Service service) {
        if (serviceRepository.existsById(service.getId()))
            throw new IllegalArgumentException("This service already exists");

        serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long service_id) {
        boolean exists = serviceRepository.existsById(service_id);
        if(!exists)
            throw new IllegalStateException("Service with id" + service_id + "does not exist");

        serviceRepository.deleteById(service_id);
    }

    @Override
    @Transactional
    public void updateService(Long serviceId, String name, Double price){
        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalStateException("Service with id" + serviceId + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(service.getName(),name))
            service.setName(name);

        if(price != null && price >0 && !Objects.equals(service.getPrice(),price))
            service.setPrice(price);
    }


}
