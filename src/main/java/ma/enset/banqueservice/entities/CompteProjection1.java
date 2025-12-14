package ma.enset.banqueservice.entities;

import org.springframework.data.rest.core.config.Projection;

// name = "p1" est le nom qu'on utilisera dans l'URL
@Projection(name = "p1", types = Compte.class)
public interface CompteProjection1 {
    public String getId();
    public String getType();
    public Double getSolde();
    // On ne met PAS dateCreation ni currency
}