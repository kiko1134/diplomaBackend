package com.example.diplomaupdated.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "register")
public class Register {
    @Id
    @SequenceGenerator(
            name = "register_sequence",
            sequenceName = "register_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "register_sequence")
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "register")
    private User user;

    @OneToOne(mappedBy = "register")
    private Workshop workshop;
}
