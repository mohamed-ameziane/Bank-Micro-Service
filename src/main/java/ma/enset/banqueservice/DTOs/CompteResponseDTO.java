package ma.enset.banqueservice.DTOs;

import java.util.Date;

public record CompteResponseDTO(
        Integer id,
        Date dateCreation,
        Double solde,
        String currency,
        String type
) {}