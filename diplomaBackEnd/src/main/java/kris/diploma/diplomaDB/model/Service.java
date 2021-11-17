package kris.diploma.diplomaDB.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Service")
public class Service {
    @Id
    @SequenceGenerator(
            name = "service_sequence",
            sequenceName = "service_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "strategy_sequence")
    private Long id;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "service")
    Set<FavoriteService> favoriteServices;

    @ManyToMany(mappedBy = "mtmServices")
    Set<Workshop> mtmWorkshops;

    public Service(){}

    public Service(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Service(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
