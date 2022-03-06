package com.example.diplomaupdated.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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
}
