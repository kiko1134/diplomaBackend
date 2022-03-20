package com.example.diplomaupdated.service;

import com.example.diplomaupdated.model.Service;

import java.util.List;

public interface ServiceService {
    List<Service> getServices();
    void addNewService(String serviceDto);
    void deleteService(Long serviceId);
    void updateService(Long serviceId,String name);
    String getServiceNameById(Long serviceId);
}
