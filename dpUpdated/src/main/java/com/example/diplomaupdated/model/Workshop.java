package com.example.diplomaupdated.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workshop")
public class Workshop {
    @Id
    @SequenceGenerator(
            name = "workshop_sequence",
            sequenceName = "workshop_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workshop_sequence")

    private Long id;
    private String phone_number;
    private String workshop_description;
    private String workshop_address;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "workshop")
    Set<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "workshop")
    Set<WorkshopService> workshopService;
}
