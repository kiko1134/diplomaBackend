package kris.diploma.diplomaDB.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Workshop")
public class Workshop {
    @Id
    @SequenceGenerator(
            name = "workshop_sequence",
            sequenceName = "workshop_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workshop_sequence")
    private Long id;
    private String email;
    private String name;
    private String phone_number;
    private String workshop_description;
    private String workshop_address;

    @ManyToMany
    @JoinTable(
            name = "workshop_services",
            joinColumns = @JoinColumn(name = "workshop_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Service> mtmServices;


    public Workshop(Long id, String email, String name, String phone_number, String workshop_description, String workshop_address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.workshop_description = workshop_description;
        this.workshop_address = workshop_address;
    }

    public Workshop(Long id, String email, String name, String phone_number, String workshop_address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.workshop_address = workshop_address;
    }

    public Workshop(String email, String name, String phone_number, String workshop_address) {
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.workshop_address = workshop_address;
    }

    public Workshop(String email, String name, String phone_number, String workshop_description, String workshop_address) {
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.workshop_description = workshop_description;
        this.workshop_address = workshop_address;
    }

    public Workshop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWorkshop_description() {
        return workshop_description;
    }

    public void setWorkshop_description(String workshop_description) {
        this.workshop_description = workshop_description;
    }

    public String getWorkshop_address() {
        return workshop_address;
    }

    public void setWorkshop_address(String workshop_address) {
        this.workshop_address = workshop_address;
    }
}


