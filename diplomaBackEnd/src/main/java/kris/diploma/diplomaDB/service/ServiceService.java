package kris.diploma.diplomaDB.service;

import kris.diploma.diplomaDB.model.Service;

import java.util.List;

public interface ServiceService {

    List<Service> getServices();

    void addNewService(Service service);

    void deleteService(Long service_id);

    void updateService(Long serviceId, String name, Double price);
}
