package kris.diploma.diplomaDB.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FavoriteServiceKey implements Serializable {

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "service_id")
    Long service_id;
}
