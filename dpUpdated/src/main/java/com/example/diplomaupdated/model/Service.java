package com.example.diplomaupdated.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service")
public class Service {

    @Id
    @SequenceGenerator(
            name = "service_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    private Long id;
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "favoriteServices")
    Set<User> user_favorites;

}
