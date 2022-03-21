package com.example.diplomaupdated.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
//@Data
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

//    @ManyToMany(mappedBy = "favoriteServices")
//    Set<User> userFavorites;

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    Set<WorkshopService> workshopService;
}
