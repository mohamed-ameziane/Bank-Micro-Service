package ma.enset.banqueservice.mappers;

import ma.enset.banqueservice.DTOs.CompteResponseDTO;
import ma.enset.banqueservice.entities.Compte;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {
    public CompteResponseDTO fromCompte(Compte compte){
        return new CompteResponseDTO(
                compte.getId(),
                compte.getDateCreation(),
                compte.getSolde(),
                compte.getCurrency(),
                compte.getType()
        );
    }
}
