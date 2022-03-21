package com.example.diplomaupdated.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workshop_service")
public class WorkshopService {
    @Id
    @SequenceGenerator(
            name = "service_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "workshop_id")
    Workshop workshop;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteServices")
    Set<User> userFavorites;

}
