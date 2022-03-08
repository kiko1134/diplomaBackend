package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.model.Service;
import com.example.diplomaupdated.repo.ServiceRepo;
import com.example.diplomaupdated.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepo serviceRepo;

    @Override
    public List<Service> getServices() {
        return serviceRepo.findAll();
    }

    @Override
    public void addNewService(String serviceName) {

        Service service = serviceRepo.existsByName(serviceName)
                .orElseThrow(() -> new IllegalStateException("this service was already added"));

        Service currentService = new Service();
        currentService.setName(serviceName);
        serviceRepo.save(service);
    }

    @Override
    public void deleteService(Long serviceId) {
        boolean exists = serviceRepo.existsById(serviceId);
        if(!exists)
            throw new IllegalStateException("Service with id" + serviceId + "does not exist");

        serviceRepo.deleteById(serviceId);
    }

    @Override
    public void updateService(Long serviceId, String name) {
        Service service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new IllegalStateException("Service with id" + serviceId + "does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(service.getName(),name))
            service.setName(name);
    }
}
