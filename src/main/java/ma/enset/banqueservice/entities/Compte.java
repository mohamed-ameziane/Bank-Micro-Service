package ma.enset.banqueservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Compte {
    @Id
    private Integer id;
    private Date dateCreation;
    private Double solde;
    private String currency;
    private String type; // "EPARGNE" ou "COURANT"
}
