package kris.diploma.diplomaBackend.model;

import javax.persistence.*;

@Entity
public class FavoriteService {
    @EmbeddedId
    FavoriteServiceKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("service_id")
    @JoinColumn(name = "serive_id")
    Service service;

    boolean favorite;

}
